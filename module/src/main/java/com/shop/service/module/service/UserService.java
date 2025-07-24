package com.shop.service.module.service;

import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends BaseService {

    List<UserEntity> getUserList();

    Result register(UserEntity user);

    UserEntity findByUsernameAndPassword(String username, String password);

    Result adminLogin(String username, String password, HttpSession session);

    Result userLogin(String username, String password, HttpSession session);


    Result getCode(HttpSession session);

    Result userLoginByCode(String username, String code, HttpSession session);

    Result insertAdmin(UserEntity userEntity);

    Result getUserListForPage(int pno, int psize, String username);

    Result updateUser(UserEntity userEntity);

    Result changePassword(UserEntity userEntity);

    Result getUserListAll();

    Result setFreeze(Long id, Integer freeze);
}
