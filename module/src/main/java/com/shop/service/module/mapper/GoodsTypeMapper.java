package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.GoodsTypeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GoodsTypeMapper extends BaseMapper<GoodsTypeEntity> {

    @Select("<script>" +
            "SELECT " +
            "  id, " +
            "  name, " +
            "  remark " +
            " from shop_goods_type " +
            "where 1 = 1 " +
            "<if test='name != null and name != \"\" '>" +
            " and name like '%${name}%' " +
            "</if>" +
            "</script>")
    Page<GoodsTypeEntity> getListForPage(Page p, @Param("name") String name);
}
