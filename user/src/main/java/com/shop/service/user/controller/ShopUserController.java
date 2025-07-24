package com.shop.service.user.controller;

import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;
import com.shop.service.module.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/shop")
@RestController
public class ShopUserController {


    @Autowired
    private ShopUserService shopUserService;


    @GetMapping("/list/page")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "username",defaultValue = "")String  username,
            @RequestParam(value = "phone",defaultValue = "")String  phone,
            @RequestParam(value = "beginTime",defaultValue = "")String  beginTime,
            @RequestParam(value = "endTime",defaultValue = "")String  endTime,
            @RequestParam(value = "freeze",required = false)Integer freeze
    ){
        return shopUserService.getListForPage(pno,psize,username,phone,beginTime,endTime,freeze);
    }

    @PutMapping("/insert")
    public Result insert(@RequestBody UserEntity userEntity){
        return shopUserService.insert(userEntity);
    }

    @GetMapping("/find/id/{id}")
    public Result findById(@PathVariable("id") Long id){
        return shopUserService.findById(id);
    }

    @PutMapping("/update")
    public  Result update(@RequestBody UserEntity userEntity){
        return shopUserService.update(userEntity);
    }

    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        return shopUserService.deleteById(id);
    }

    @GetMapping("/set/freeze")
    public Result setFreeze(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "freeze") Integer freeze
    ){
        return shopUserService.setFreeze(id,freeze);
    }

    @PostMapping("/login/password")
    public Result loginPassword(
            @RequestBody UserEntity userEntity
    ){
        return
                shopUserService.loginPassword(userEntity.getUsername(),userEntity.getPassword());

}
    @PostMapping("/register")
    public Result register(@RequestBody UserEntity userEntity){
        return shopUserService.register(userEntity.getUsername(),userEntity.getPassword());
    }

}
