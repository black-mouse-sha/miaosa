package com.shop.service.shop.controller;

import com.shop.service.module.entity.GoodsTypeEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/goods-type")
@RestController
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping("/list/page")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "name",defaultValue = "")String name
    ){
        return goodsTypeService.getListForPage(pno,psize,name);
    }

    @PutMapping("/insert")
    public Result insert(@RequestBody GoodsTypeEntity goodsTypeEntity){
        return goodsTypeService.insert(goodsTypeEntity);
    }

    @GetMapping("/find/id/{id}")
    public Result findById(@PathVariable("id") Long id){
        return goodsTypeService.findById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody GoodsTypeEntity goodsTypeEntity){
        return goodsTypeService.update(goodsTypeEntity);
    }

    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        return goodsTypeService.deleteById(id);
    }

    @GetMapping("/list/all")
    public Result getListAll(){
        return goodsTypeService.getListAll();
    }

}
