package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("<script>" +
            "select " +
            " sa.id as id, " +
            " sa.username as username, " +
            " sa.nickname as nickname, " +
            " sa.face as face, " +
            " sa.role_id as role_id, " +
            " sr.role_name as role_name, " +
            " sd.name as dept_name, " +
            " sa.insert_time as insert_time, " +
            " sa.phone as phone, " +
            " sa.email as email, " +
            " sa.freeze as freeze " +
            "from shop_admin sa " +
            "left join shop_role sr on sa.role_id = sr.id " +
            "left join shop_dept sd on sd.id = sa.dept_id " +
            "where 1 = 1 and sa.type=0 " +
            "" +
            "<if test='username != null and username !=\"\"'> " +
            " and username like '%${username}%'" +
            "</if>" +
            "</script>")
    Page<UserEntity> getUserListForPage(Page<UserEntity> page, @Param(value = "username") String username);

    @Select(
            "select " +
            " sa.id as id, " +
            " sa.username as username, " +
            " sa.nickname as nickname, " +
            " sa.face as face, " +
            " sa.role_id as role_id, " +
            " sr.role_name as role_name, " +
            " sd.name as dept_name, " +
            " sa.insert_time as insert_time, " +
            " sa.phone as phone, " +
            " sa.email as email, " +
            " sa.freeze as freeze " +
            "from shop_admin sa " +
            "left join shop_role sr on sa.role_id = sr.id " +
            "left join shop_dept sd on sd.id = sa.dept_id " +
            "where 1 = 1 and sa.type=0 "
            )
    List<UserEntity> getUserListAll();
    @Select("<script>" +
            "select " +
            " id, " +
          " username, " +
          " nickname, " +
          " `password`, " +
          " type, " +
          " phone, " +
          " email, " +
          " face, " +
          " freeze, " +
          " insert_time, " +
          " sex " +
          " from shop_admin " +
          " where type = 1 " +
          "<if test='freeze != null'> " +
          " and freeze = ${freeze} " +
          "</if>" +
          "<if test='phone != null and phone != \"\"'> " +
          " and phone like '%${phone}%'" +
          " </if>" +
          "<if test='username != null and username != \"\"'> " +
          " and username like '%${username}%' " +
          " </if>" +
          "<if test='beginTime != null and beginTime != \"\"'> " +
          " and unix_timestamp(insert_time) &gt;= unix_timestamp('${beginTime} 00:00:00') " +
          " </if>" +
          "<if test='endTime != null and endTime != \"\"'> " +
          " and unix_timestamp(insert_time) &lt;= unix_timestamp('${endTime} 23:59:59') " +
          " </if>" +
          "</script>")
    Page<UserEntity> getShopUserListForPage(
            //分⻚查询对象，需要将pno和psize注⼊
            Page<UserEntity> p,
            //@Param对象可以将传⼊的参数动态添加到sql中指定的取值位置
            @Param("username") String username,
            @Param("phone") String phone,
            @Param("freeze") Integer freeze,
            @Param("beginTime") String biginTime,
            @Param("endTime") String endTime
    );
}
