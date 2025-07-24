package com.shop.service.module.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.DeptEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.UserEntity;
import com.shop.service.module.mapper.DeptMapper;
import com.shop.service.module.mapper.UserMapper;
import com.shop.service.module.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Result getListForPage(int pno, int psize, String name) {
        Page<DeptEntity> p = new Page<>(pno,psize);
        Page<DeptEntity> pageResult = deptMapper.getDeptListForPage(p, name);
        List<DeptEntity> list = pageResult.getRecords();
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
    public Result insert(DeptEntity deptEntity) {
        if(deptEntity.getName() == null || deptEntity.getName().trim().length() == 0){
            return Result.end(500,"","部门名称不可以为空");
        }
        int updateCount = deptMapper.insert(deptEntity);
        if(updateCount > 0){
            return Result.end(200, "", "新增成功");
        }else{
            return Result.end(500, "", "新增失败");
        }

    }

    @Override
    public Result findById(Long id) {
        DeptEntity dept = deptMapper.selectById(id);
        return Result.end(200,dept,"查询成功");
    }

    @Override
    public Result update(DeptEntity deptEntity) {
        if(deptEntity.getName() == null || deptEntity.getName().trim().length() == 0){
            return Result.end(500,"","部门名称不可以为空");
        }
        int updateCount = deptMapper.updateById(deptEntity);
        if(updateCount > 0){
            return Result.end(200, "", "修改成功");
        }else{
            return Result.end(500, "", "修改失败");
        }
    }

    @Override
    public Result deleteById(Long id) {
        int updateCount = deptMapper.deleteById(id);
        if(updateCount > 0){
            return Result.end(200, "", "删除成功");
        }else{
            return Result.end(500, "", "删除失败");
        }
    }

    @Autowired
    private UserMapper userMapper;
    @Override
    public Result getListUser() {
        List<DeptEntity> list = deptMapper.selectList(null);
        list.stream().forEach( dept -> {
            QueryWrapper<UserEntity> q = new QueryWrapper<>();
            q.eq("dept_id",dept.getId());
            q.eq("type",0);
            List<UserEntity> userList = userMapper.selectList(q);
            dept.setChildren(userList);
        });
        return Result.end(200,list,"查询成功");
    }

    @Override
    public Result addUser(Long[] ids, Long id) {
        Arrays.stream(ids).forEach(userId -> {
            UserEntity user = userMapper.selectById(userId);
            user.setDeptId(Integer.valueOf(id.toString()));
            userMapper.updateById(user);
        });
        return Result.end(200,"","新增成功");
    }

    @Override
    public Result deleteUser(Long id) {
        UserEntity user = userMapper.selectById(id);
        user.setDeptId(-1);
        int updateCount = userMapper.updateById(user);
        if(updateCount>0){
            return Result.end(200,"","删除成功");
        }else{
            return Result.end(500,"","删除失败");
        }

    }

}
