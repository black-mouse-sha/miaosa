package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.GoodsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GoodsMapper extends BaseMapper<GoodsEntity> {


    @Select("<script>" +
            "select " +
            " sg.id," +
            " sg.name," +
            " sg.goods_type_id," +
            " sg.discount," +
            " sg.price," +
            " sg.description," +
            " sg.remark," +
            " sg.count," +
            " sg.sale_count," +
            " sg.logo," +
            " sg.pics," +
            " sg.is_on_sale," +
            " sg.insert_time," +
            " sgt.name as goods_type_name" +
            " from shop_goods sg" +
            " left join shop_goods_type sgt on sg.goods_type_id = sgt.id" +
            " where 1 = 1 " +
            "<if test='name!=null and name!=\"\"'>" +
            " and sg.name like '%${name}%'"+
            "</if>" +
            "<if test='goodsTypeId!=null'>" +
            " and sg.goods_type_id = ${goodsTypeId}"+
            "</if>" +
            "<if test='isOnSale!=null'>" +
            " and sg.is_on_sale = ${isOnSale}"+
            "</if>" +
            "</script>")
    Page<GoodsEntity> getGoodsListForPage(
            Page p,
            @Param("name") String name,
            @Param("goodsTypeId") Long goodsTypeId,
            @Param("isOnSale") Integer isOnSale
    );
}
