package com.shop.service.module.service;

import com.alibaba.fastjson.JSONObject;
import com.shop.service.module.entity.OrderEntity;
import com.shop.service.module.entity.Result;

public interface OrderService extends BaseService {
    Result insertOrder(OrderEntity orderEntity, JSONObject userInfo);



    Result send(Long id, String postCode);


    Result fallbackGoods(OrderEntity order);

    Result fallbackGoodsOver(Long id);


}
