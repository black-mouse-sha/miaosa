package com.shop.service.mqclient.reciver;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.shop.service.module.entity.OrderEntity;
import com.shop.service.module.entity.TeamEntity;
import com.shop.service.module.mapper.OrderMapper;
import com.shop.service.module.mapper.TeamMapper;
import com.shop.service.module.mapper.TeamUserMapper;
import com.shop.service.module.util.RedisLockHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = {"orderTimeoutDeadQueue"})
public class OrderTimeoutReciver {

    @Autowired
    private RedisLockHelper redisLockHelper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RabbitHandler
    public void process(JSONObject data, Message message, Channel channel){

        String orderId = data.get("orderId").toString();
        String userId = data.get("userId").toString();
        //由于死信队列的默认配置走的是并发通知所以会产生多线程操作所以这里我们仍然要使用锁。
        //并且使用分布式锁实现同步模式
        String timeOutKey = "orderTimeout";
        boolean lock = redisLockHelper.lock(timeOutKey);
        while (lock ==false){
            lock = redisLockHelper.lock(timeOutKey);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(lock);
        //使用手动事务处理
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);

        try{
            //获取订单mapper
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            //获取TeamUserMapper
            TeamUserMapper teamUserMapper = sqlSession.getMapper(TeamUserMapper.class);
            //获取TeamMpper
            TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
            //查询订单数据
            OrderEntity order = orderMapper.selectById(orderId);

            //判断订单状态如果仍然是未支付，就将订单删除，将用户从活动中删除，将库存退回
            if(order!=null && order.getStatus() == 0){
                String teamDataKey = "team_data_"+order.getTeamId();
                //删除订单
                orderMapper.deleteById(order.getId());

                QueryWrapper q = new QueryWrapper();
                q.eq("user_id",userId);
                q.eq("team_id",order.getTeamId());
                //删除秒杀参与人
                teamUserMapper.delete(q);
                //更新内存和数据库中的拼团人数
                TeamEntity teamData = (TeamEntity) redisTemplate.opsForValue().get(teamDataKey);
                teamData.setHasMember(teamData.getHasMember() - 1);
                teamData.setStatus(0);
                redisTemplate.opsForValue().getAndSet(teamDataKey,teamData);
                teamMapper.updateById(teamData);
                //提交事务
                sqlSession.commit();
                sqlSession.close();
            }
            sqlSession.close();
            //释放锁
            redisLockHelper.delete(timeOutKey);
        }catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            sqlSession.rollback();
            sqlSession.close();
            //释放锁
            redisLockHelper.delete(timeOutKey);
        }

        //配置应答
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

