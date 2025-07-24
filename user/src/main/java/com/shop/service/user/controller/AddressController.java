package com.shop.service.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.shop.service.module.entity.AddressEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/address")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/list/page")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1",required = false)
            int pno,
            @RequestParam(value = "psize",defaultValue = "10",required = false)
            int psize,
            HttpServletRequest request
    ){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        authorization = authorization.replace("Bearer ","").trim();
        Jwt tokenJwt = JwtHelper.decode(authorization);
        String tokenStr = tokenJwt.getClaims();
        System.out.println(tokenStr);
        JSONObject tokenJSON = JSONObject.parseObject(tokenStr);
        JSONObject userJson = JSONObject.parseObject(
                tokenJSON.get("userInfo").toString()
        );
        String id = userJson.get("id").toString();
//        return null;
        return addressService.getListForPage(pno,psize,id);
    }

    @PutMapping("/insert")
    public Result insert(
            @RequestBody AddressEntity addressEntity,
            HttpServletRequest request
    ){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        authorization = authorization.replace("Bearer ","").trim();
        Jwt tokenJwt = JwtHelper.decode(authorization);
        String tokenStr = tokenJwt.getClaims();
        System.out.println(tokenStr);
        JSONObject tokenJSON = JSONObject.parseObject(tokenStr);
        JSONObject userJson = JSONObject.parseObject(
                tokenJSON.get("userInfo").toString()
        );
        String id = userJson.get("id").toString();
        addressEntity.setUserId(Long.valueOf(id));
        return addressService.insert(addressEntity);
    }

    @GetMapping("/find/id/{id}")
    public Result findById(
            @PathVariable(value = "id") Long id
    ){
        return addressService.findById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody AddressEntity addressEntity){
        return addressService.update(addressEntity);
    }

    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(
            @PathVariable("id") Long id
    ){
        return addressService.deleteById(id);
    }
}
