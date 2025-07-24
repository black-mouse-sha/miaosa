package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.GoodsTypeEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.GoodsTypeMapper;
import com.shop.service.module.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public Result getListForPage(int pno, int psize, String name) {
        Page<GoodsTypeEntity> p = new Page<GoodsTypeEntity>(pno,psize);

        Page<GoodsTypeEntity> pageResult = goodsTypeMapper.getListForPage(p, name);
        List<GoodsTypeEntity> list = pageResult.getRecords();
        JSONObject data = new JSONObject();
        JSONObject page = new JSONObject();
        page.put("pno",pno);
        page.put("psize",psize);
        page.put("pCount",pageResult.getPages());
        page.put("totalElements",pageResult.getTotal());
        data.put("list",list);
        data.put("page",page);

        return Result.end(200,data,"查询成功");
    }

    @Override
    public Result insert(GoodsTypeEntity goodsTypeEntity) {
        int updateCount = goodsTypeMapper.insert(goodsTypeEntity);
        if(updateCount>0){
            return Result.end(200,"","新增成功");
        }else{
            return Result.end(500,"","新增失败");
        }

    }

    @Override
    public Result findById(Long id) {
        GoodsTypeEntity goodsType = goodsTypeMapper.selectById(id);

        return Result.end(200,goodsType,"查询成功");
    }

    @Override
    public Result update(GoodsTypeEntity goodsTypeEntity) {
        int updateCount = goodsTypeMapper.updateById(goodsTypeEntity);
        if(updateCount>0){
            return Result.end(200,"","修改成功");
        }else{
            return Result.end(500,"","修改失败");
        }

    }

    @Override
    public Result deleteById(Long id) {
        int updateCount = goodsTypeMapper.deleteById(id);
        if(updateCount>0){
            return Result.end(200,"","删除成功");
        }else{
            return Result.end(500,"","删除失败");
        }
    }

    @Override
    public Result getListAll() {
        List<GoodsTypeEntity> list = goodsTypeMapper.selectList(null);
        JSONObject data = new JSONObject();
        data.put("list",list);
        return Result.end(200,data,"查询成功");
    }

}
