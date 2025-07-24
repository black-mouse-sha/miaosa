package com.shop.service.module.service;

import com.shop.service.module.entity.Result;

public interface MenuService extends BaseService {
    Result getMenuByRoleId(String roleId);

    Result getMenuTableData();


}
