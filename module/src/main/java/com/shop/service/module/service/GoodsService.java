package com.shop.service.module.service;

import com.shop.service.module.entity.GoodsEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.GoodsMapper;

public interface GoodsService {

    Result getGoodsListForPage(int pno, int psize, String name, Long goodsTypeId, Integer isOnSale);

    Result setOnSale(Long id, Integer isOnSale);


    Result insertOne(GoodsEntity goodsEntity, String s);


    Result findById(Long id, String s);


    Result updateByMapper(GoodsEntity goodsEntity, Class<GoodsMapper> goodsMapperClass);


    Result deleteById(Long id, String s);


    Result getListAll(Object o, Class<GoodsMapper> goodsMapperClass);




}
