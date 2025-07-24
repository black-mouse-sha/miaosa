package com.shop.service.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.RoleEntity;
import com.shop.service.module.mapper.RoleMapper;
import com.shop.service.module.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequestMapping("/role")
@RestController
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list/all")
    public Result getRoleListAll(){
        return roleService.getRoleListAll();
    }

    @Valid
    @GetMapping("/find/id/{id}")
    public Result findRoleById(@NotNull(message = "id不可以为空") @PathVariable("id") Long id){
        return roleService.findById(id,"com.shop.service.module.mapper.RoleMapper.selectById");
    }

    @GetMapping("/list/page")
    public Result getRoleListForPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
          @RequestParam(value = "psize",defaultValue = "10") int psize
    ){
        Page<RoleEntity> p = new Page(pno,psize);
        QueryWrapper q = new QueryWrapper();
        return roleService.getListForPage(p,q, RoleMapper.class);
    }

    @Valid
    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@NotNull(message = "id不可以为空") @PathVariable("id") Long id){
        return roleService.deleteById(id,"com.shop.service.module.mapper.RoleMapper.deleteById");
    }

    @PutMapping("/insert")
    public Result insert(@Validated @RequestBody RoleEntity roleEntity){
        return roleService.insertOne(roleEntity,"com.shop.service.module.mapper.RoleMapper.insert");
    }

    @PutMapping("/update")
    public Result update(@Validated @RequestBody RoleEntity roleEntity){
        return roleService.updateByMapper(roleEntity,RoleMapper.class);
    }

}
