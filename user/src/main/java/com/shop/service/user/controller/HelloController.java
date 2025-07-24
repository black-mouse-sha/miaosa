package com.shop.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.service.module.entity.LogEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;
import com.shop.service.module.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequestMapping("/test")
@RestController
public class HelloController {

    @Autowired
    TestFeignService testFeignService;


    @GetMapping("/feign/insert")
    public Result insert(){
        LogEntity log = new LogEntity();
        log.setUserNickname("aaa");
        log.setInsertTime(new Date());
        log.setUserAccount("bbb");
        log.setUserId(1l);
        log.setMethod("put");
        String res = testFeignService.insert(log);
        System.out.println(res);

        return JSONObject.parseObject(res,Result.class);
    }

    @GetMapping("/feign/test")
    public String testFeign(){
        String res = testFeignService.hello();
        System.out.println(res);
        return res;
    }

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @GetMapping("/get/json1")
    public Result getJSON1(){
        return Result.end(200,"返回的数据","请求成功");
    }

    @GetMapping("/get/json2")
    public Result getJSON2(){
        UserEntity u = new UserEntity();
        u.setInsertTime(new Date());
        u.setUsername("admin");
        u.setPassword("123456");
        u.setType(0);
        return Result.end(200,u,"请求成功");
    }

    @GetMapping("/get/json3")
    public Result getJSON3(){
        List<UserEntity> list = new ArrayList<>();
        UserEntity u1 = new UserEntity();
        u1.setInsertTime(new Date());
        u1.setUsername("admin");
        u1.setPassword("123456");
        u1.setType(0);

        UserEntity u2 = new UserEntity();
        u2.setInsertTime(new Date());
        u2.setUsername("admin1");
        u2.setPassword("1234561");
        u2.setType(1);
        list.add(u1);
        list.add(u2);
        return Result.end(200,list,"请求成功");
    }

    @GetMapping("/get/json4")
    public Result getJSON4(){
        List<UserEntity> list = new ArrayList<>();
        UserEntity u1 = new UserEntity();
        u1.setInsertTime(new Date());
        u1.setUsername("admin");
        u1.setPassword("123456");
        u1.setType(0);

        UserEntity u2 = new UserEntity();
        u2.setInsertTime(new Date());
        u2.setUsername("admin1");
        u2.setPassword("1234561");
        u2.setType(1);
        list.add(u1);
        list.add(u2);

        JSONObject data = new JSONObject();
        data.put("list",list);

        return Result.end(200,data,"请求成功");
    }

    @GetMapping("/get/json5")
    public Result getJSON5(){
        JSONObject data = new JSONObject();
        data.put("list","list");
        data.put("page","page");

        return Result.end(200,data,"请求成功");
    }

    @GetMapping("/get/list")
    public Result getList(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "name",defaultValue = "") String name
    ){
        System.out.println(pno);
        System.out.println(psize);
        System.out.println(name);
        List list = new ArrayList();
        JSONObject data = new JSONObject();
        data.put("list",list);
        return Result.end(200,data,"查询成功");
    }

    @GetMapping("/find/id/{id}")
    public Result findById(
            @PathVariable(value = "id",required = false)Long id
    ){
        System.out.println(id);
        return Result.end(200,id,"请求成功");
    }

    @PutMapping("/insert/user")
    public Result insertUser(@RequestBody() UserEntity user){
        if(user.getUsername() == null || user.getUsername().trim().length() == 0){
            return Result.end(500,"","账号不可以为空");
        }

        if(user.getPassword() == null || user.getPassword().trim().length() == 0){
            return Result.end(500,"","密码不可以为空");
        }

        if(user.getNickname() == null || user.getNickname().trim().length() == 0){
            return Result.end(500,"","昵称不可以为空");
        }

        return Result.end(200,user,"新增成功");
    }

    @PostMapping("/login")
    public Result login(
            @RequestParam(value = "username",defaultValue = "")String username,
            @RequestParam(value = "password",defaultValue = "")String password
    ){
        if(username== null || username.trim().length() == 0){
            return Result.end(500,"","账号不可以为空");
        }

        if(password == null || password.trim().length() == 0){
            return Result.end(500,"","密码不可以为空");
        }

        if("admin".equals(username) && "123456".equals(password)){
            return Result.end(200,"","登录成功");
        }else{
            return Result.end(500,"","用户名或密码错误");
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        if(id == 1){
            return Result.end(200,"","删除成功");
        }else{
            return Result.end(500,"","删除失败");
        }
    }
}
