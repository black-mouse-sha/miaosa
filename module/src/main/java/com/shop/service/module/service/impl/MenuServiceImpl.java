package com.shop.service.module.service.impl;

import com.shop.service.module.entity.MenuEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.MenuMapper;
import com.shop.service.module.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public Result getMenuByRoleId(String roleId) {

        List<MenuEntity> list = menuMapper.getMenuListByRoleId(Long.valueOf(roleId));
        System.out.println(list);
        List<Map<String,Object>> listMenu = new ArrayList<>();
        list.stream().forEach(item -> {
            if(item.getPid() == -1){
                Map<String,Object> map = new HashMap<>();
                map.put("id",item.getId());
                map.put("name",item.getName());
                map.put("url",item.getUrl());
                map.put("icon",item.getIcon());
                map.put("pid",item.getPid());
                listMenu.add(map);
            }
        });
        listMenu.stream().forEach(baseItem -> {
            list.stream().forEach(item -> {
                if(baseItem.get("id").equals(item.getPid())){
                    List<Map<String,Object>> itemList = (List<Map<String,Object>>)baseItem.get("children");
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",item.getName());
                    map.put("url",item.getUrl());
                    map.put("icon",item.getIcon());
                    map.put("pid",item.getPid());
                    map.put("id",item.getId());
                    if(itemList == null){
                        itemList = new ArrayList<>();
                        itemList.add(map);
                    }else{
                        itemList.add(map);
                    }
                    baseItem.put("children",itemList);
                }
            });
        });
        return Result.end(200,listMenu,"查询成功");
    }

    @Override
    public Result getMenuTableData() {
        List<MenuEntity> list = menuMapper.selectList(null);
        List<Map<String,Object>> listMenu = new ArrayList<>();
        list.stream().forEach(item -> {
            if(item.getPid() == -1){
                Map<String,Object> map = new HashMap<>();
                map.put("id",item.getId());
                map.put("name",item.getName());
                map.put("url",item.getUrl());
                map.put("icon",item.getIcon());
                map.put("pid",item.getPid());
                listMenu.add(map);
            }
        });
        listMenu.stream().forEach(baseItem -> {
            list.stream().forEach(item -> {
                if(baseItem.get("id").equals(item.getPid())){
                    List<Map<String,Object>> itemList = (List<Map<String,Object>>)baseItem.get("children");
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",item.getName());
                    map.put("url",item.getUrl());
                    map.put("icon",item.getIcon());
                    map.put("pid",item.getPid());
                    map.put("id",item.getId());
                    if(itemList == null){
                        itemList = new ArrayList<>();
                        itemList.add(map);
                    }else{
                        itemList.add(map);
                    }
                    baseItem.put("children",itemList);
                }
            });
        });

        return Result.end(200,listMenu,"查询成功");
    }


}
