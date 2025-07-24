package com.shop.service.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.GoodsEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.GoodsMapper;
import com.shop.service.module.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PutMapping("/insert")
    @CacheEvict(value = "goodsList",allEntries = true)
    public Result insert(@RequestBody GoodsEntity goodsEntity){
        goodsEntity.setInsertTime(new Date());
        goodsEntity.setIsOnSale(0);
        goodsEntity.setSaleCount(0);
        return goodsService.insertOne(goodsEntity,"com.shop.service.module.mapper.GoodsMapper.insert");
    }

    @GetMapping("/find/id/{id}")
    public Result findGoodsById(@PathVariable("id") Long id){
        return goodsService.findById(id,"com.shop.service.module.mapper.GoodsMapper.selectById");
    }

    @PutMapping("/update")
    @CacheEvict(value = "goodsList",allEntries = true)
    public Result update(@RequestBody GoodsEntity goodsEntity){
        return goodsService.updateByMapper(goodsEntity, GoodsMapper.class);
    }

    @DeleteMapping("/delete/id/{id}")
    @CacheEvict(value = "goodsList",allEntries = true)
    public Result deleteById(@PathVariable("id") Long id){
        return goodsService.deleteById(id,"com.shop.service.module.mapper.GoodsMapper.deleteById");
    }


    @GetMapping("/list/all")
    public Result getListAll(){
        return goodsService.getListAll(null,GoodsMapper.class);
    }

    @Cacheable(value = "goodsList",condition = "true")
    @GetMapping("/list/page")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1",required = false) int pno,
            @RequestParam(value = "psize",defaultValue = "10",required = false) int psize,
            @RequestParam(value = "name",defaultValue = "",required = false)String name,
            @RequestParam(value = "goodsTypeId",required = false)Long goodsTypeId,
            @RequestParam(value = "isOnSale",required = false)Integer isOnSale

    ){
        return goodsService.getGoodsListForPage(pno,psize,name,goodsTypeId,isOnSale);
    }

    @GetMapping("/set/onsale")
    @CacheEvict(value = "goodsList",allEntries = true)
    public Result setOnSale(
            @RequestParam("id") Long id,
            @RequestParam("isOnSale") Integer isOnSale
    ){
        return goodsService.setOnSale(id,isOnSale);
    }



}
