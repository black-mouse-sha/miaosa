package com.shop.service.user.controller;

import com.shop.service.module.entity.DeptEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.service.DeptService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list/page")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "name",defaultValue = "")String name
    ){
        return deptService.getListForPage(pno,psize,name);
    }

    @PutMapping("/insert")
    public Result insert(@RequestBody() DeptEntity deptEntity){
        return deptService.insert(deptEntity);
    }

    @GetMapping("/find/id/{id}")
    public Result findById(@PathVariable("id") Long id){
        return deptService.findById(id);
    }

    @PutMapping("update")
    public Result update(@RequestBody() DeptEntity deptEntity){
        return deptService.update(deptEntity);
    }

    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        return deptService.deleteById(id);
    }


    @GetMapping("/list/user")
    public Result getListUser(){
        return deptService.getListUser();
    }

    @PutMapping("/add/user")
    public Result addUser(@RequestParam("ids") Long[] ids,@RequestParam("id") Long id){
        return deptService.addUser(ids,id);
    }

    @DeleteMapping("/delete/user")
    public Result deleteUser(@RequestParam("id") Long id){
        return deptService.deleteUser(id);
    }
}
