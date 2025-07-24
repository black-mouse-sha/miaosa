package com.shop.service.module.service;

import com.shop.service.module.entity.DeptEntity;
import com.shop.service.module.entity.Result;

public interface DeptService {
    Result getListForPage(int pno, int psize, String name);

    Result insert(DeptEntity deptEntity);

    Result findById(Long id);

    Result update(DeptEntity deptEntity);

    Result deleteById(Long id);

    Result getListUser();

    Result addUser(Long[] ids, Long id);

    Result deleteUser(Long id);
}
