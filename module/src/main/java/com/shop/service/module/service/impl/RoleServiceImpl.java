package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.module.entity.MenuRoleEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.RoleEntity;
import com.shop.service.module.mapper.RoleMapper;
import com.shop.service.module.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result getRoleListAll() {
        List<RoleEntity> list = roleMapper.selectList(null);
        JSONObject j = new JSONObject();
        j.put("list",list);
        return Result.end(200,j,"查询成功");
    }

    @Override
    public Result findRoleById(Long id) {
        QueryWrapper<RoleEntity> q = new QueryWrapper<>();
        q.eq("id",id);
        RoleEntity r = roleMapper.selectOne(q);
        return Result.end(200,r,"查询成功");
    }

}
