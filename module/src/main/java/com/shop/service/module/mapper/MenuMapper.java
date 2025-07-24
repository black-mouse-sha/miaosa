package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.module.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuEntity> {

    @Select("select " +
            "sm.id as id," +
            "sm.name as `name`," +
            "sm.icon as icon," +
            "sm.url as url," +
            "sm.is_link as is_link," +
            "sm.remark as remark," +
            "sm.pid as pid," +
            "sm.level as level " +
            " from shop_menu as sm " +
            " left join shop_menu_role smr on sm.id = smr.menu_id " +
            " left join shop_role sr on smr.role_id = sr.id " +
            " where sr.id = #{roleId}")
    List<MenuEntity> getMenuListByRoleId(@Param("roleId") Long roleId);
}
