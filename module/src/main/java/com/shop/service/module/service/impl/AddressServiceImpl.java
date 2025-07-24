package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.AddressEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.AddressMapper;
import com.shop.service.module.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Result getListForPage(int pno, int psize, String id) {

        Page<AddressEntity> p = new Page<>(pno,psize);
        Page<AddressEntity> pageResult = addressMapper.getAddressListForPage(
                p, id
        );
        List<AddressEntity> list = pageResult.getRecords();
        JSONObject data = new JSONObject();
        data.put("list",list);
        JSONObject page = new JSONObject();
        page.put("pno",pno);
        page.put("psize",psize);
        page.put("pCount",pageResult.getPages());
        page.put("totalElements",pageResult.getTotal());
        data.put("page",page);
        return Result.end(200,data,"查询成功");
    }

    @Override
    public Result insert(AddressEntity addressEntity) {
        Integer isDefault = addressEntity.getIsDefault();
        Long userId = addressEntity.getUserId();
        if(isDefault == 1){
            QueryWrapper<AddressEntity> q = new QueryWrapper<>();
            q.eq("user_id",userId);
            List<AddressEntity> list = addressMapper.selectList(q);
            list.stream().forEach(address -> {
                if(address.getIsDefault() == 1){
                    address.setIsDefault(0);
                    addressMapper.updateById(address);
                }
            });
        }
        int updateCount = addressMapper.insert(addressEntity);
        if(updateCount>0){
            return Result.end(200,"","新增成功");
        }else{
            return Result.end(500,"","新增失败");
        }

    }

    @Override
    public Result findById(Long id) {
        AddressEntity address = addressMapper.selectById(id);
        return Result.end(200,address,"查询成功");
    }

    @Override
    public Result update(AddressEntity addressEntity) {
        if(addressEntity.getIsDefault() == 1){
            QueryWrapper<AddressEntity> q = new QueryWrapper();
            q.eq("user_id",addressEntity.getUserId());
            List<AddressEntity> list = addressMapper.selectList(q);
            list.stream().forEach( address -> {
                if(address.getIsDefault() == 1){
                    address.setIsDefault(0);
                    addressMapper.updateById(address);
                }
            });
        }
        int updateCount = addressMapper.updateById(addressEntity);
        if(updateCount>0){
            return Result.end(200,"","修改成功");
        }else{
            return Result.end(500,"","修改失败");
        }
    }

    @Override
    public Result deleteById(Long id) {
        int updateCount = addressMapper.deleteById(id);
        if(updateCount>0){
            return Result.end(200,"","删除成功");
        }else{
            return Result.end(500,"","删除失败");
        }
    }
}
