package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.AddressEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.TeamEntity;
import com.shop.service.module.entity.TeamUserEntity;
import com.shop.service.module.mapper.AddressMapper;
import com.shop.service.module.mapper.TeamMapper;
import com.shop.service.module.mapper.TeamUserMapper;
import com.shop.service.module.service.TeamService;
import com.shop.service.module.util.RedisLockHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeamServiceImpl extends BaseServiceImpl implements TeamService {

    @Autowired
    private TeamUserMapper teamUserMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Result getListForPage(int pno, int psize, String name, Integer isOnSale, Integer type) {
        Page<TeamEntity> p = new Page<>(pno,psize);
        Page<TeamEntity> pageResult = teamMapper.getTeamListForPage(p, name, isOnSale, type);
        List<TeamEntity> list = pageResult.getRecords();
        JSONObject page = new JSONObject();
        page.put("pno",pno);
        page.put("psize",psize);
        page.put("pCount",pageResult.getPages());
        page.put("totalElements",pageResult.getTotal());
        JSONObject data = new JSONObject();
        data.put("list",list);
        data.put("page",page);
        return Result.end(200,data,"查询成功");
    }

    @Override
    public Result insert(TeamEntity teamEntity) {
        //设置活动创建时间
        teamEntity.setInsertTime(new Date());
        //设置活动参与人为0
        teamEntity.setHasMember(0);
        //设置活动状态初始值
        teamEntity.setStatus(0);
        //设置上架状态为下架
        teamEntity.setIsOnSale(0);
        int updateCount = teamMapper.insert(teamEntity);
        if(updateCount>0){
            JSONObject data = new JSONObject();
            data.put("id",teamEntity.getId());
            //获取当前时间
            long timestamp = new Date().getTime();
            //获取截止时间
            long endTimestamp = teamEntity.getEndTime().getTime();
            //得到剩余多久过期
            long expireTime = endTimestamp - timestamp;
            System.out.println(expireTime);
            //通知消息队列在一定期限内设置活动过期
            rabbitTemplate.convertAndSend("teamTimeOutExchange",
                    "teamTimeOutRouter",
                    data,
                    message -> {
                //设置等待时间
                        message.getMessageProperties().setExpiration(expireTime+"");
                        return message;
                    }
                    );


            return Result.end(200,"","新增成功");
        }else{
            return Result.end(500,"","新增失败");
        }

    }

    @Override
    public Result findById(Long id) {
        TeamEntity team = teamMapper.selectById(id);
        return Result.end(200,team,"查询成功");
    }

    @Override
    public Result update(TeamEntity teamEntity) {
        int updateCount = teamMapper.updateById(teamEntity);
        if(updateCount>0){
            return Result.end(200,"","修改成功");
        }else{
            return Result.end(500,"","修改失败");
        }
    }

    @Override
    public Result deleteById(Long id) {
        int updateCount = teamMapper.deleteById(id);
        if(updateCount>0){
            return Result.end(200,"","删除成功");
        }else{
            return Result.end(500,"","删除失败");
        }
    }

    @Override
    public Result setOnSale(Long id, Integer isOnSale) {
        TeamEntity team = teamMapper.selectById(id);
        team.setIsOnSale(isOnSale);
        int updateCount = teamMapper.updateById(team);
        if(updateCount>0){
            return Result.end(200,"","设置成功");
        }else{
            return Result.end(500,"","设置失败");
        }
    }

    @Override
    public void makeActivityTimeout(Long id) {

        TeamEntity team = teamMapper.selectById(id);
        if(team!= null){
            team.setStatus(1);
            teamMapper.updateById(team);
        }

    }

    @Override
    public Result getMyTeamListForPage(int pno, int psize, Long id, Integer type) {
        Page<TeamEntity> p = new Page<>(pno,psize);
        Page<TeamEntity> page = teamMapper.getMyTeamListForPage(p,type,id);
        JSONObject data = this.setPageResult(page.getRecords(), page);
        return Result.end(200,data,"查询成功");
    }

    @Override
    public Result getTeamUserList(Long id) {
        List<TeamUserEntity> list = teamUserMapper.getTeamUserList(id);
        JSONObject data  = new JSONObject();
        data.put("list",list);
        return Result.end(200,data,"查询成功");
    }

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public Result inserTeam(Long id, String userId) {
        String lockKey = "team"+id;
        int maxCount = 5;
//        boolean lock = redisLockHelper.lock(lockKey);
        boolean lock = true;
//        while (lock == false && maxCount > 0){
//            lock = redisLockHelper.lock(lockKey);
//            maxCount--;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        if(lock == false){
//            return Result.end(500,"","当前参数人数过多");
//        }

        String teamKey = "team_data_"+id;
        TeamEntity team = (TeamEntity) redisTemplate.opsForValue().get(teamKey);
        if(team == null){
            team = teamMapper.selectById(id);
        }
        if(team.getStatus()!=0){
            redisLockHelper.delete(lockKey);
            return Result.end(500,"","活动已结束或拼满");
        }
        if(team.getHasMember()>=team.getSize()){
            redisLockHelper.delete(lockKey);
            team.setStatus(2);
            return Result.end(500,"","活动已拼满");
        }

        QueryWrapper<AddressEntity> q = new QueryWrapper();
        q.eq("is_default",1).eq("user_id",userId);
        List<AddressEntity> addressList = addressMapper.selectList(q);
        if(addressList.size()==0){
            redisLockHelper.delete(lockKey);
            return Result.end(500,"","您还没有设置默认收货地址");
        }

        QueryWrapper<TeamUserEntity> q1 = new QueryWrapper<>();
        q1.eq("user_id",userId);
        q1.eq("team_id",id);
        List<TeamUserEntity> teamUserList = teamUserMapper.selectList(q1);
        if(teamUserList.size()>0){
            redisLockHelper.delete(lockKey);
            return Result.end(500,"","您已经参加过该活动");
        }

        Integer hasMember = team.getHasMember();
        team.setHasMember(hasMember+1);
        redisTemplate.opsForValue().getAndSet(teamKey,team);

        JSONObject data = new JSONObject();
        data.put("id",id);
        data.put("userId",userId);
        rabbitTemplate.convertAndSend(
                "orderExchange",
                "orderRouter",
                data
        );


        redisLockHelper.delete(lockKey);
        return Result.end(200,"","活动参与成功请等待订单生成");
    }

    @Override
    public Result getResult(Long id, String userId) {
        String teamKey = "team"+id+userId;
        Object res = redisTemplate.opsForValue().get(teamKey);
        if(res == null){
            return Result.end(101,"","订单生成中");
        }else{
            return Result.end(200,"","订单创建成功请在15分钟内完成支付");
        }
    }
}
