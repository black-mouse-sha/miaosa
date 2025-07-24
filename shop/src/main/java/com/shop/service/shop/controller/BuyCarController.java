package com.shop.service.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.service.module.entity.BuyCarEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.BuyCarMapper;
import com.shop.service.module.service.BuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequestMapping("/buy-car")
@RestController
public class BuyCarController {
    @Autowired
    private BuyCarService buyCarService;


    @GetMapping("/list")
    @Cacheable(value="buyCar",key = "#id+''+#isDeal")
    public Result getListForPage(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "isDeal") Integer isDeal,
            HttpServletRequest request
    ){


        return   buyCarService.getBuyCarListForPage(id,isDeal);

    }
    @GetMapping("/find/id/{id}")
    public Result findById(@PathVariable("id") Long id){
        return   buyCarService.findById(id,"com.shop.service.module.mapper.BuyCarMapper.selectById");
    }

    @PutMapping("/insert")
    @CacheEvict(value = "buyCar",allEntries = true)
    public  Result insert (@RequestBody BuyCarEntity buyCarEntity, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token!=null){

            Jwt tokenObj = JwtHelper.decode(token.replace("Bearer ", "").trim());
            JSONObject jtoken = JSONObject.parseObject(tokenObj.getClaims());
            JSONObject userInfo = JSONObject.parseObject(jtoken.get("userInfo").toString());
            buyCarEntity.setUserId(Long.valueOf(userInfo.get("id").toString()));
            buyCarEntity.setBuyCount(1);
            buyCarEntity.setIsDeal(0);
            buyCarEntity.setInsertTime(new Date());
            return   buyCarService.insertBuyCar(buyCarEntity);
        }else{
            return Result.end(500,"","用户未登录");
        }
    }

    @PutMapping("/update")
    @CacheEvict(value = "buyCar",allEntries = true)
    public Result update(@RequestBody BuyCarEntity BuyCarEntity){
        return   buyCarService.updateByMapper(BuyCarEntity,BuyCarMapper.class);
    }

    @DeleteMapping("/delete/id/{id}")
    @CacheEvict(value = "buyCar",allEntries = true)
    public Result deleteById(@PathVariable("id") Long id){
        return   buyCarService.deleteById(id,"com.shop.service.module.mapper.BuyCarMapper.deleteById");
    }
}
