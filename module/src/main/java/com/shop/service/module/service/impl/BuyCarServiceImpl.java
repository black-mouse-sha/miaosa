package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.module.entity.BuyCarEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.BuyCarMapper;
import com.shop.service.module.service.BuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyCarServiceImpl extends BaseServiceImpl implements BuyCarService {

    @Autowired
    private BuyCarMapper buyCarMapper;


    @Override
    public Result getBuyCarListForPage(Long id, Integer isDeal) {
        List<BuyCarEntity> list = buyCarMapper.getBuyCarList(id, isDeal);
        JSONObject data = new JSONObject();
        data.put("list",list);
        return Result.end(200,data,"查询成功");
    }

    @Override
    public Result insertBuyCar(BuyCarEntity buyCarEntity) {
        QueryWrapper<BuyCarEntity> q = new QueryWrapper();
        q.eq("user_id",buyCarEntity.getUserId());
        q.eq("goods_id",buyCarEntity.getGoodsId());
        q.eq("is_deal",0);

        BuyCarEntity b = buyCarMapper.selectOne(q);
        if(b!=null){
            return Result.end(500,"","该商品已经在购物车中");
        }
        buyCarMapper.insert(buyCarEntity);
        return Result.end(200,"","新增成功");
    }
}
