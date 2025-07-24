package com.shop.service.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.TeamEntity;
import com.shop.service.module.mapper.TeamMapper;
import com.shop.service.module.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/team")
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;


    @GetMapping("/list/page")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1",required = false) int pno,
            @RequestParam(value = "psize",defaultValue = "10",required = false) int psize,
            @RequestParam(value = "name",defaultValue = "",required = false)String name,
            @RequestParam(value = "isOnSale",required = false)Integer isOnSale,
            @RequestParam(value = "type",required = false)Integer type
    ){
        return teamService.getListForPage(pno,psize,name,isOnSale,type);
    }

    @PutMapping("/insert")
    public Result insert(@RequestBody TeamEntity teamEntity){
        return teamService.insert(teamEntity);
    }

    @GetMapping("/find/id/{id}")
    public  Result findById( @PathVariable("id") Long id){
        return teamService.findById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody TeamEntity teamEntity){
        return teamService.update(teamEntity);
    }

    @DeleteMapping("/delete/id/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        return teamService.deleteById(id);
    }

    @GetMapping("/set/onsale")
    public Result setOnSale(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "isOnSale") Integer isOnSale
    ){
        return teamService.setOnSale(id,isOnSale);
    }

    @GetMapping("/list/alive")
    public Result getListAlive(){
        QueryWrapper q = new QueryWrapper();
        q.eq("status",0);
        q.apply("UNIX_TIMESTAMP(current_time) < UNIX_TIMESTAMP(end_time)");
        return teamService.getListAll(q, TeamMapper.class);
    }

    @GetMapping("/list/user/team_id/{id}")
    public Result getTeamUserList(@PathVariable("id") Long id){
        return teamService.getTeamUserList(id);
    }


    @GetMapping("/list/page/me")
    public Result getListForPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "type",required = false) Integer type,
            HttpServletRequest request

    ){
        String token = request.getHeader("Authorization");
        Jwt tokenObj = JwtHelper.decode(token.replace("Bearer ", "").trim());
        JSONObject jtoken = JSONObject.parseObject(tokenObj.getClaims());
        JSONObject userInfo = JSONObject.parseObject(jtoken.get("userInfo").toString());
        return teamService.getMyTeamListForPage(pno,psize,Long.valueOf(userInfo.get("id").toString()),type);
    }


    @PostMapping("/insert/team")
    public Result insertTeam(
        @RequestParam("id") Long id,
        HttpServletRequest request
    ){
        //从header中提取鉴权字符串
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        //从加密字符串中去掉Bearer字样
        authorization = authorization.replace("Bearer ","");
        //通过jwt解密工具将密文解密成原始对象
        Jwt jwtInfo = JwtHelper.decode(authorization);
        //将jwt原始对象转换成字符串
        String jwtStr = jwtInfo.getClaims();
        System.err.println(jwtStr);
        //将jwt的字符串转换成json对象
        JSONObject jwtJSON = JSONObject.parseObject(jwtStr);
        //将userInfo这个属性的结果提取并转换成json对象，得到用户信息的json数据
        JSONObject userObject = JSONObject.parseObject(jwtJSON.get("userInfo").toString());
        //从用户信息中提取用户id
        String userId = userObject.get("id").toString();
        //输出用户id
        System.err.println("请求用户的id为"+id);
        return teamService.inserTeam(id,userId);
    }


    @GetMapping("/get/result")
    public Result getResult(
            @RequestParam("id") Long id,
            HttpServletRequest request
    ){
        //从header中提取鉴权字符串
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        //从加密字符串中去掉Bearer字样
        authorization = authorization.replace("Bearer ","");
        //通过jwt解密工具将密文解密成原始对象
        Jwt jwtInfo = JwtHelper.decode(authorization);
        //将jwt原始对象转换成字符串
        String jwtStr = jwtInfo.getClaims();
        System.err.println(jwtStr);
        //将jwt的字符串转换成json对象
        JSONObject jwtJSON = JSONObject.parseObject(jwtStr);
        //将userInfo这个属性的结果提取并转换成json对象，得到用户信息的json数据
        JSONObject userObject = JSONObject.parseObject(jwtJSON.get("userInfo").toString());
        //从用户信息中提取用户id
        String userId = userObject.get("id").toString();
        //输出用户id
        System.err.println("请求用户的id为"+id);
        return teamService.getResult(id,userId);
    }
}
