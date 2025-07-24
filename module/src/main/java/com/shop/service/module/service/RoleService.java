package com.shop.service.module.service;

import com.shop.service.module.entity.Result;

public interface RoleService extends BaseService{
    Result getRoleListAll();

    Result findRoleById(Long id);
}
