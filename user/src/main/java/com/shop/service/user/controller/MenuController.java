package com.shop.service.user.controller;

import com.shop.service.module.entity.MenuEntity;
import com.shop.service.module.entity.MenuRoleEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.MenuMapper;
import com.shop.service.module.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequestMapping("/menu")
@RestController
@Validated
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Valid
    @PostMapping(value = "/list/all/role/{roleId}")
    public Result getMenuByRoleId(@NotBlank(message = "角色id不可以为空") @PathVariable("roleId") String roleId){
        return menuService.getMenuByRoleId(roleId);
    }

    @GetMapping("/list/all")
    public Result getMenuTableData(){
        return menuService.getMenuTableData();
    }

    @PutMapping("/insert")
    public Result insertMenu(@Validated @RequestBody MenuEntity menuEntity){
        menuEntity.setPid(Long.valueOf(-1));
        return menuService.insertOne(menuEntity,"com.shop.service.module.mapper.MenuMapper.insert");
    }

    @Valid
    @PutMapping("/insert/pid/{pid}")
    public Result insertMenuChild(
            @Validated @RequestBody MenuEntity menuEntity,
            @NotNull(message = "pid不可以为空") @PathVariable("pid") Long pid){
        menuEntity.setPid(pid);
        return menuService.insertOne(menuEntity,"com.shop.service.module.mapper.MenuMapper.insert");
    }
    @Valid
    @GetMapping("/find/id/{id}")
    public Result findById(@NotNull(message = "id不可以为空") @PathVariable("id")Long id ){
        return menuService.findById(id,"com.shop.service.module.mapper.MenuMapper.selectById");
    }

    @PutMapping("/update")
    public Result update(@Validated @RequestBody MenuEntity menuEntity){
        return menuService.updateByMapper(menuEntity, MenuMapper.class);
    }

    @Valid
    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@NotNull(message = "id不可以为空") @PathVariable("id")Long id ){
        return menuService.deleteById(id,"com.shop.service.module.mapper.MenuMapper.deleteById");
    }

    @PostMapping("/insert/menu-role")
    public Result insertMenuRole(@Validated @RequestBody MenuRoleEntity menuRoleEntity){
        return menuService.insertMenuRole(menuRoleEntity);
    }
}
