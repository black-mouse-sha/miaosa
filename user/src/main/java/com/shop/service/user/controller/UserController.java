package com.shop.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;
import com.shop.service.module.feign.TokenService;
import com.shop.service.module.mapper.UserMapper;
import com.shop.service.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


@RequestMapping("/admin")
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @GetMapping("/list/all")
    public Result getListAll(){
        return userService.getUserListAll();
    }




    @PostMapping("/login")
    public Result login(@Validated @RequestBody(required = false) UserEntity user, HttpSession session){
        try{
            return userService.adminLogin(user.getUsername(),user.getPassword(),session);
        }catch (Exception e){
            e.printStackTrace();
            return Result.end(500,e.toString(),"发生异常");
        }
    }



    @PutMapping("/insert")
    public Result insertUser(@Validated @RequestBody UserEntity userEntity){
        userEntity.setInsertTime(new Date());
        userEntity.setType(0);
        userEntity.setFreeze(0);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        return userService.insertOne(userEntity,"com.shop.service.module.mapper.UserMapper.insert");
    }

    @GetMapping("/list/page")
    public Result getUserListForPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "username",required = false,defaultValue = "") String username
    ){
        return userService.getUserListForPage(pno,psize,username);
    }

    @Valid
    @GetMapping("/find/id/{id}")
    public Result findById(@NotNull(message = "id不可以为空") @PathVariable("id") Long id){
        return userService.findById(id,"com.shop.service.module.mapper.UserMapper.selectById");
    }
    @Valid
    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@NotNull(message = "id不可以为空") @PathVariable("id") Long id){
        return userService.deleteById(id,"com.shop.service.module.mapper.UserMapper.deleteById");
    }

    @PutMapping("/update")
    public Result updateUser(@Validated @RequestBody UserEntity user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(user.getPassword()!=null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return userService.updateByMapper(user, UserMapper.class);
    }

    @PostMapping("/password/change")
    public Result changePass(@Validated @RequestBody UserEntity userEntity){
        return userService.changePassword(userEntity);
    }
}
