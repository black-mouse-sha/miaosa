package com.shop.service.oauth.service.impl;


import com.shop.service.oauth.entity.UserEntity;
import com.shop.service.oauth.mapper.UserMapper;
import com.shop.service.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserEntity> getUserList() {
        return userMapper.selectList(null);
    }
}
