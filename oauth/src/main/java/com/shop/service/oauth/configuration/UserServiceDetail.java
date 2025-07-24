package com.shop.service.oauth.configuration;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.oauth.entity.UserEntity;
import com.shop.service.oauth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 在鉴权触发的时候会执行loadUserByUsername函数，这里需要我们自行处理
 * 如读取数据库获取用户信息，再将用户的数据返回即可，外面的加密设置会针对登陆的参数和用户信息进行对比
 */
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> q = new QueryWrapper();
        q.eq("username",username);
        UserEntity user = userMapper.selectOne(q);
        System.out.println(user);

        if(user!=null){
            System.out.println(user);

            return user;
        }else{
            return user;

        }

    }
}
