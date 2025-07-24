package com.shop.service.module.service;

import com.shop.service.module.entity.BuyCarEntity;
import com.shop.service.module.entity.Result;

public interface BuyCarService extends BaseService{
    Result getBuyCarListForPage(Long id, Integer isDeal);

    Result insertBuyCar(BuyCarEntity buyCarEntity);
}
