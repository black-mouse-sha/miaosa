package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;
import com.shop.service.module.feign.TokenService;
import com.shop.service.module.mapper.UserMapper;
import com.shop.service.module.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getListForPage(int pno, int psize, String username, String phone, String beginTime, String endTime, Integer freeze) {

        Page<UserEntity> p = new Page<>(pno,psize);
        Page<UserEntity> pageResult = userMapper.getShopUserListForPage(p, username, phone, freeze, beginTime, endTime);
        List<UserEntity> list = pageResult.getRecords();
        System.out.println(list);

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
    public Result insert(UserEntity userEntity) {
        if(userEntity.getUsername() == null || userEntity.getUsername().trim().length() == 0){
            return Result.end(500,"","账号不可以为空");
        }

        if(userEntity.getPassword() == null || userEntity.getPassword().trim().length() == 0){
            return Result.end(500,"","密码不可以为空");
        }

        if(userEntity.getFace() == null || userEntity.getFace().trim().length() == 0){
            return Result.end(500,"","头像不可以为空");
        }
        if(userEntity.getNickname() == null || userEntity.getNickname().trim().length() == 0){
            return Result.end(500,"","昵称不可以为空");
        }

        if(userEntity.getPhone() == null || userEntity.getPhone().trim().length() == 0){
            return Result.end(500,"","手机号码不可以为空不可以为空");
        }

        if(userEntity.getSex() == null ){
            return Result.end(500,"","性别不可以为空不可以为空");
        }
        QueryWrapper<UserEntity> q = new QueryWrapper<UserEntity>();
        q
                .eq("username",userEntity.getUsername())
                .or()
                .eq("phone",userEntity.getPhone());
        List<UserEntity> list = userMapper.selectList(q);
        if(list.size() == 0){

            userEntity.setInsertTime(new Date());
            userEntity.setFreeze(0);
            userEntity.setType(1);
            String password = new BCryptPasswordEncoder().encode(userEntity.getPassword());
            userEntity.setPassword(password);
            int updateCount = userMapper.insert(userEntity);
            if(updateCount>0){
                return Result.end(200,"","新增成功");
            }else{
                return Result.end(500,"","新增失败");
            }
        }else{
            return Result.end(500,"","账号或手机号码重复请更换");
        }


    }

    @Override
    public Result findById(Long id) {
        //这里介绍一个根据id查询数据的快捷方式每个mapper都存在selectById可以根据主键返回数据
        UserEntity user = userMapper.selectById(id);
        return Result.end(200,user,"查询成功");
    }

    @Override
    public Result update(UserEntity userEntity) {
        int updateCount = userMapper.updateById(userEntity);
        if(updateCount>0){
            return Result.end(200,"","修改成功");
        }else{
            return Result.end(500,"","修改失败");
        }

    }

    @Override
    public Result deleteById(Long id) {
        int updateCount = userMapper.deleteById(id);
        if(updateCount>0){
            return Result.end(200,"","删除成功");
        }else{
            return Result.end(500,"","删除失败");
        }
    }

    @Override
    public Result setFreeze(Long id, Integer freeze) {

        UserEntity user = userMapper.selectById(id);
        user.setFreeze(freeze);
        int updateCount = userMapper.updateById(user);
        if(updateCount>0){
            return Result.end(200,"","设置成功");
        }else{
            return Result.end(500,"","设置失败");
        }
    }
    @Autowired
    private TokenService tokenService;
    @Override
    public Result loginPassword(String username, String password) {
        //借助mybatis-plus的条件查询对象快捷的先根据用户账号查询用户
        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("type",1);
        q.eq("username",username);
        UserEntity user = userMapper.selectOne(q);
        System.out.println(user);
        //如果查询结果为空说明账号不正确
        if(user == null){
            //这里必须提示账号或密码错误以防止非本人操作尝试账号
            return Result.end(500,"","账号或密码错误");
        }
        //查询有结果之后验证密码的逻辑
        //获取用户在数据库中的密码
        String passwordEncoded = user.getPassword();
        //实例化bcrypt加密工具
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder);
        System.out.println(password);
        /**
         * 由于bcrypt每次加密结果不同所以不能直接用加密工具得到和数据库一样的密码
         * 所以我们采用加密工具内置的密码验证函数matches进行验证，他会尝试密码和加密结果是否能单向
         * 生成并返回一个boolean值，如果是true代表该组密码是正确的
         */
        //如果返回false则代表密码错误
        if(passwordEncoder.matches(password,passwordEncoded) == false){
            return Result.end(500,"","账号或密码错误");
        }
        //密码验证通过后再验证冻结状态
//        if(user.getFreeze() == 1){
//            return Result.end(500,"","您的账号已经被冻结暂时无法登录");
//        }
        //如果密码验证通过就需要进行token的获取，这里需要使用feign调用oauth的授权服务

        //通过tokenService调用鉴权系统
        /**
         * username:用户账号
         * password:用户源密码，oauth服务中会再次验证密码
         * grant_type:配置使用密码鉴权,这里输入password
         * client_id:发送鉴权请求的客户端id oauth中定义为client_2
         * client_secret:鉴权客户端的密码这里配置好了123456
         * scope：是oauth中配置好的依然输入client_2
         */
        String res = tokenService.getToken(
                username,
                password,
                "password",
                "client_2",
                "123456",
                "client_2");
        //如果调用oauth服务失败会触发TokenService的熔断返回的是{code:500}这个结构
        //如果成功返回的是jwt的结构
        //将feign返回的结果转成json对象
        JSONObject jsonRes = JSONObject.parseObject(res);
        //判断如果返回中带有code 500字样代表调用接口失败
        if("500".equals(jsonRes.get("code"))){
            return Result.end(500,"","用户鉴权失败");
        }
        //通过上面的判断代表全部成功，封装结果集
        JSONObject data = new JSONObject();
        data.put("userInfo",user);
        data.put("jwt",jsonRes);
        return Result.end(200,data,"登录成功");
    }


    @Override
    public Result register(String username, String password) {

        QueryWrapper<UserEntity> q = new QueryWrapper<>();
        q.eq("username",username);
        q.eq("type",1);
        UserEntity user = userMapper.selectOne(q);
        if(user!= null){
            return Result.end(500,"","该用户已经被注册");
        }
        user = new UserEntity();
        user.setPhone(username);
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setInsertTime(new Date());
        user.setType(1);
        user.setFreeze(0);
        int updateCount = userMapper.insert(user);
        if(updateCount>0){
            return Result.end(200,"","注册成功");
        }else{
            return Result.end(500,"","注册失败");
        }
    }

}
