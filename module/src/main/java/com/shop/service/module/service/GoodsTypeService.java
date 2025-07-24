package com.shop.service.module.service;

import com.shop.service.module.entity.GoodsTypeEntity;
import com.shop.service.module.entity.Result;

public interface GoodsTypeService {
    Result getListForPage(int pno, int psize, String name);

    Result insert(GoodsTypeEntity goodsTypeEntity);

    Result findById(Long id);

    Result update(GoodsTypeEntity goodsTypeEntity);

    Result deleteById(Long id);

    Result getListAll();
}
