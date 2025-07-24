package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shop.service.module.entity.GoodsEntity;
import com.shop.service.module.entity.OrderEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.GoodsMapper;
import com.shop.service.module.mapper.OrderMapper;
import com.shop.service.module.mapper.TeamMapper;
import com.shop.service.module.service.OrderService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result insertOrder(OrderEntity orderEntity, JSONObject userInfo) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        String password = orderEntity.getPassword();
        String userPassword = userInfo.get("password").toString();
        BCryptPasswordEncoder p = new BCryptPasswordEncoder();
        if(password == null){
            sqlSession.rollback();
            sqlSession.close();
            return Result.end(500,"","支付密码不可以为空");
        }
        if(p.matches(password,userPassword) == false){
            sqlSession.rollback();
            sqlSession.close();
            return Result.end(500,"","支付密码错误");
        }
        GoodsEntity goods = goodsMapper.selectById(orderEntity.getGoodsId());
        if(orderEntity.getCount() > goods.getCount()){
            sqlSession.rollback();
            sqlSession.close();
            return Result.end(500,"","库存不足");
        }
        goods.setCount(goods.getCount()-orderEntity.getCount());
        goods.setSaleCount(goods.getSaleCount()+orderEntity.getCount());
        goodsMapper.updateById(goods);
        String orderNo = "SPDD-"+new Date().getTime()+ UUID.randomUUID().toString().split("-")[0];
        orderEntity.setOrderNo(orderNo);
        orderEntity.setStatus(1);
        int res = orderMapper.insert(orderEntity);

        System.out.println(res);
        sqlSession.commit();
        sqlSession.close();
        return Result.end(200,orderEntity,"支付成功");


    }



    @Override
    public Result send(Long id, String postCode) {
        OrderEntity order = orderMapper.selectById(id);
        order.setExpressNo(postCode);
        order.setStatus(2);
        orderMapper.updateById(order);
        return Result.end(200,"","发货成功");
    }



    @Override
    public Result fallbackGoods(OrderEntity order) {
        OrderEntity orderEntity = orderMapper.selectById(order.getId());
        orderEntity.setFallbackImg(order.getFallbackImg());
        orderEntity.setFallbackReason(order.getFallbackReason());
        orderEntity.setFallbackTime(new Date());
        orderEntity.setStatus(4);
        orderMapper.updateById(orderEntity);
        return Result.end(200,"","退款申请成功");
    }

    @Override
    public Result fallbackGoodsOver(Long id) {
        OrderEntity order = orderMapper.selectById(id);
        order.setStatus(5);
        orderMapper.updateById(order);
        return Result.end(200,"","退款处理成功");
    }



}
