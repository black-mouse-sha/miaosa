package com.shop.service.mqclient.reciver;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import com.shop.service.module.entity.*;
import com.shop.service.module.mapper.*;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Component
@RabbitListener(queues = {"orderQueue"})
public class OrderQueueReciver {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void process(JSONObject data, Message message, Channel channel){
        //使用手动事务处理
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        //获取商品查询对象
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        //获取活动查询对象
        TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
        //获取收货地址查询对象
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        //获取订单查询对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        //获取活动参与人表的查询对象
        TeamUserMapper teamUserMapper = sqlSession.getMapper(TeamUserMapper.class);
        //假设消息发送成功从中取出活动id和用户id
        String id = data.get("id").toString();
        String userId = data.get("userId").toString();
        String teamDatakey = "team_data_"+id;
        //从内存中获取活动对象
        TeamEntity teamData = (TeamEntity)redisTemplate.opsForValue().get(teamDatakey);
        try{

            //提取商品id
            Long goodsId = teamData.getGoodsId();
            //根据id查询商品信息
            GoodsEntity goods = goodsMapper.selectById(goodsId);
            //查询用户的默认收货地址
            QueryWrapper<AddressEntity> q = new QueryWrapper<>();
            q.eq("user_id",userId);
            q.eq("is_default",1);
            AddressEntity address = addressMapper.selectOne(q);

            //实例化订单对象
            OrderEntity order = new OrderEntity();
            //封装订单信息
            order.setOrderNo("HDDD"+System.currentTimeMillis()+ UUID.randomUUID().toString().split("-")[0]);
            order.setActivityDiscount(teamData.getTeamDiscount());
            order.setGoodsDiscount(goods.getDiscount());
            order.setGoodsId(goods.getId());
            order.setGoodsName(goods.getName());
            order.setGoodsPrice(goods.getPrice());
            order.setActivityName(teamData.getName());
            order.setAddress(address.getAddress());
            order.setName(address.getName());
            order.setArea(address.getArea());
            order.setAddressId(address.getId());
            order.setPhone(address.getPhone());
            order.setProvince(address.getProvince());
            order.setCity(address.getCity());
            order.setPay(goods.getPrice()*goods.getDiscount()/100*teamData.getTeamDiscount()/100);
            order.setCount(1);
            order.setTeamId(teamData.getId());
            order.setUserId(Long.valueOf(userId));
            order.setPostCode(address.getPostCode());
            order.setInsertTime(new Date());
            order.setRemark("活动下单");
            order.setStatus(0);
            order.setType(teamData.getType() == 0?3:4);
            //创建订单
            orderMapper.insert(order);
            //实例化TeamUser对象
            TeamUserEntity teamUserEntity = new TeamUserEntity();
            teamUserEntity.setUserId(Long.valueOf(userId));
            teamUserEntity.setTeamId(Long.valueOf(id));
            //保存活动参与人记录
            teamUserMapper.insert(teamUserEntity);

            //更新数据库的活动数据
            teamMapper.updateById(teamData);
            //事务提交
            sqlSession.commit();
            sqlSession.close();

            //在redis内存中针对用户和活动创建一个订单成功的标记
            String orderCompleteKey = "team"+id+userId;
            //设置十五分钟过期
            redisTemplate.opsForValue().set(orderCompleteKey,1, Duration.ofMinutes(15));
            //通知死信队列订单过期
            //通知死信队列订单过期
            JSONObject data1 = new JSONObject();
            data1.put("userId",userId);
            data1.put("orderId",order.getId());
            rabbitTemplate.convertAndSend(
                    "orderTimeoutExchange",
                    "orderTimeoutRouter",
                    data1,
                    message1 -> {
                        message1.getMessageProperties().setExpiration((15*60*1000)+"");
                        return message1;
                    }
            );

        }catch (Exception e){
            //一旦出现异常就回滚事务
            sqlSession.rollback();
            sqlSession.close();
            //发生异常之后需要回退缓存中的已参与人数
            teamData.setHasMember(teamData.getHasMember() - 1);
            redisTemplate.opsForValue().getAndSet(teamDatakey,teamData);

        }



        try {
            //告诉rabbit已经成功消费该消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}