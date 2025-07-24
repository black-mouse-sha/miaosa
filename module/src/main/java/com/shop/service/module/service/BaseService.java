package com.shop.service.module.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.MenuRoleEntity;
import com.shop.service.module.entity.Result;

import java.util.List;

public interface BaseService {
    JSONObject setPageResult(List list, Page page);

    Result selectOne(QueryWrapper q,Class MapperClass);

    Result findById(Long id, String templateMethod);

    Result insertOne(Object obj, String templaetMethod);

    Result updateOne(Object obj, String templateMethod);

    Result deleteById(Long id, String templateMethod);

    Result updateByMapper(Object object, Class MapperClass) ;

    Result getListForPage(Page page, QueryWrapper q, Class MapperClass);

    Result insertMenuRole(MenuRoleEntity menuRoleEntity);

    Result getListAll(QueryWrapper q, Class MapperClass);
}
