package com.shop.service.module.service;

import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;

public interface ShopUserService {
    Result getListForPage(int pno, int psize, String username, String phone, String beginTime, String endTime, Integer freeze);

    Result insert(UserEntity userEntity);

    Result findById(Long id);

    Result update(UserEntity userEntity);

    Result deleteById(Long id);

    Result setFreeze(Long id, Integer freeze);

    Result loginPassword(String username, String password);

    Result register(String username, String password);

}
