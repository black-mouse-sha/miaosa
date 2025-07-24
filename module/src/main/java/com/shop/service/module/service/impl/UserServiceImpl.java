package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;
import com.shop.service.module.feign.TokenService;
import com.shop.service.module.mapper.UserMapper;
import com.shop.service.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Component
public  class UserServiceImpl  extends BaseServiceImpl  implements UserService{


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<UserEntity> getUserList() {
        return userMapper.selectList(null);
    }


    @Override
    public Result register(UserEntity user) {
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("username",user.getUsername());
        UserEntity u = userMapper.selectOne(q);
        if(u!=null){
            return Result.end(500,"","账号已经被注册");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setInsertTime(new Date());
        user.setPhone(user.getUsername());
        user.setType(1);
        user.setFreeze(0);
        int res = userMapper.insert(user);
        if(res>0){
            return Result.end(200,"","注册成功");
        }else{
            return Result.end(500,"","注册失败");
        }

    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("username",username);
        UserEntity u =  userMapper.selectOne(q);
        if(u == null){
            return null;
        }else{
            boolean res = new BCryptPasswordEncoder().matches(password, u.getPassword());
            return res?u:null;
        }
    }

    @Override
    public Result adminLogin(String username, String password, HttpSession session) {
        QueryWrapper<UserEntity> q = new QueryWrapper<>();

        q.select("id",
                "username",
                "password",
                "role_id",
                "dept_id",
                "freeze",
                "password",
                "nickname",
                "face",
                "email",
                "insert_time",
                "type",
                "sex").eq("username",username).eq("type",0);
        UserEntity u =  userMapper.selectOne(q);

        if(u==null){
            return Result.end(500,null,"用户名或密码错误");
        }else{
            if(new BCryptPasswordEncoder().matches(password, u.getPassword()) == false){
                return Result.end(500,null,"用户名或密码错误");
            }
        }
        System.out.println("返回远程数据");
        System.out.println(u);
        System.out.println("返回远程数据完毕");
        String str = tokenService.getToken(username,
                password,
                "password",
                "client_2",
                "123456",
                "client_2");
        System.out.println("返回远程数据");
        System.out.println(str);
        System.out.println("返回远程数据完毕");
        JSONObject json = JSON.parseObject(str);

        if("500".equals(json.get("code"))){
            return Result.end(500,json.get("msg"),"授权失败");
        }
        session.setAttribute("userInfo",JSONObject.toJSONString(u));
        Date insertTime = u.getInsertTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        u.setPassword(null);
        String userJsonString = JSONObject.toJSONString(u);
        System.out.println(userJsonString);
        JSONObject userJson = JSONObject.parseObject(userJsonString);
        userJson.put("insertTime",sdf.format(insertTime));
        JSONObject res = new JSONObject();
        res.put("userInfo",userJson);
        res.put("jwt",json);
        return Result.end(200,res,"成功");
    }

    @Override
    public Result userLogin(String username, String password, HttpSession session) {
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("username",username).eq("type",1);
        UserEntity u =  userMapper.selectOne(q);

        if(u==null){
            return Result.end(500,null,"用户名或密码错误");
        }else{
            if(u.getFreeze() == 1){
                return Result.end(500,null,"用户被冻结暂时无法登录");
            }
            if(new BCryptPasswordEncoder().matches(password, u.getPassword()) == false){
                return Result.end(500,null,"用户名或密码错误");
            }
        }
        System.out.println(u);
        String str = tokenService.getToken(username,
                password,
                "password",
                "client_2",
                "123456",
                "client_2");
        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        if("500".equals(json.get("code"))){
            return Result.end(500,json.get("msg"),"授权失败");
        }
        session.setAttribute("userInfo",JSONObject.toJSONString(u));

        Date insertTime = u.getInsertTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        u.setPassword(null);
        String userJsonString = JSONObject.toJSONString(u);
        System.out.println(userJsonString);
        JSONObject userJson = JSONObject.parseObject(userJsonString);
        userJson.put("insertTime",sdf.format(insertTime));
        JSONObject res = new JSONObject();
        res.put("userInfo",userJson);
        res.put("jwt",json);
        return Result.end(200,res,"成功");
    }

    @Override
    public Result getCode(HttpSession session) {
        long randomNum = (long)(Math.random()*1000000);
        String str = String.valueOf(randomNum).substring(1);
        session.setAttribute("code",str);
        return Result.end(200,str,"获取成功");
    }

    @Override
    public Result userLoginByCode(String username, String code, HttpSession session) {
        Object codeSession = session.getAttribute("code");
        if(codeSession == null){
            return Result.end(500,"","验证码失效");
        }
        if(!code.equals(codeSession)){
            return Result.end(500,"","验证码不正确");
        }
        QueryWrapper<UserEntity> q = new QueryWrapper();
        q.eq("username",username);
        UserEntity u = userMapper.selectOne(q);
        if(u == null){
            return Result.end(500,"","手机号码不正确");
        }
        String str = tokenService.getToken(username,
                "password",
                "client_credentials",
                "client_1",
                "123456",
                "client_1");
        JSONObject json = JSON.parseObject(str);
        if("500".equals(json.get("code"))){
            return Result.end(500,json.get("msg"),"授权失败");
        }
        session.setAttribute("userInfo",u);
        Date insertTime = u.getInsertTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        u.setPassword(null);
        String userJsonString = JSONObject.toJSONString(u);
        System.out.println(userJsonString);
        JSONObject userJson = JSONObject.parseObject(userJsonString);
        userJson.put("insertTime",sdf.format(insertTime));
        JSONObject res = new JSONObject();
        res.put("userInfo",userJson);
        res.put("jwt",json);
        return Result.end(200,res,"成功");

    }

    @Override
    public Result insertAdmin(UserEntity userEntity) {
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("username",userEntity.getUsername());
        UserEntity u = userMapper.selectOne(q);
        if(u!=null){
            return Result.end(500,"","账号已经被注册");
        }
        userEntity.setInsertTime(new Date());
        userEntity.setFreeze(0);
        userEntity.setType(0);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        int res = userMapper.insert(userEntity);
        if(res>0){
            return Result.end(200,null,"添加成功");
        }else{
            return Result.end(500,null,"添加失败");
        }

    }

    @Override
    public Result getUserListForPage(int pno, int psize, String username) {
        System.out.println(pno);
        System.out.println(psize);
        Page<UserEntity> page = new Page<>(pno,psize);
//        QueryWrapper<UserEntity> q = new QueryWrapper<>();
//        if(username.trim().length()>0){
//            q.like("username","%"+username+"%");
//        }

//        Page<UserEntity> pageRes = userMapper.selectPage(page, q);
        Page<UserEntity> pageRes = userMapper.getUserListForPage(page, username);
        List<UserEntity> list = pageRes.getRecords();
        JSONObject data = this.setPageResult(list,pageRes);
        return Result.end(200,data,"查询成功");
    }

    @Override
    public Result updateUser(UserEntity userEntity) {
        int res = userMapper.updateById(userEntity);
        if(res>0){
            return Result.end(200,"","修改成功");
        }else{
            return Result.end(500,"","修改失败");
        }
    }

    @Override
    public Result changePassword(UserEntity userEntity) {
        QueryWrapper q = new QueryWrapper();
        q.eq("username",userEntity.getUsername());
        UserEntity oldUser = userMapper.selectOne(q);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String oldPassword = oldUser.getPassword();
        String newPassword = userEntity.getPassword();
        String oldPassword1 = userEntity.getOldPassword();

        if(!bcrypt.matches(oldPassword1,oldPassword)){
            return Result.end(500,"","原密码输入错误");
        }
        if(bcrypt.matches(newPassword,oldPassword)){
            return Result.end(500,"","原密码与新密码不能一样");
        }
        oldUser.setPassword(bcrypt.encode(newPassword));
        int res = userMapper.updateById(oldUser);
        if(res>0){
            return Result.end(200,"","修改成功");
        }else{
            return Result.end(500,"","修改失败");
        }

    }

    @Override
    public Result getUserListAll() {
        List<UserEntity> list = userMapper.getUserListAll();
        JSONObject j = new JSONObject();
        j.put("list",list);
        return Result.end(200,j,"查询成功");
    }

    @Override
    public Result setFreeze(Long id, Integer freeze) {
        UserEntity user = userMapper.selectById(id);
        user.setFreeze(freeze);
        userMapper.updateById(user);
        return Result.end(200,"","设置成功");
    }
}
