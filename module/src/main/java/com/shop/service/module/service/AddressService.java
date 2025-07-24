package com.shop.service.module.service;

import com.shop.service.module.entity.AddressEntity;
import com.shop.service.module.entity.Result;

public interface AddressService {
    Result getListForPage(int pno, int psize, String id);

    Result insert(AddressEntity addressEntity);

    Result findById(Long id);

    Result update(AddressEntity addressEntity);

    Result deleteById(Long id);
}
