package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.module.entity.BuyCarEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BuyCarMapper  extends BaseMapper<BuyCarEntity> {
    
    @Select("select   " +
            " sbc.id,  " +
            " sbc.goods_id,  " +
            " sbc.user_id,  " +
            " sbc.is_deal,  " +
            " sbc.insert_time,  " +
            " sbc.buy_count,  " +
            " sg.count as goods_count,  " +
            " sg.name as goods_name,    " +
            " sg.price as goods_price,    " +
            " sg.discount as goods_discount,    " +
            " sg.description as goods_description,    " +
            " sg.remark as goods_remark,    " +
            " sg.count as goods_count,    " +
            " sg.logo as goods_logo,  " +
            " sbc.order_no " +
            " from shop_buy_car sbc  " +
            " left join shop_goods sg on sbc.goods_id = sg.id  " +
            " where user_id = ${id} and sbc.is_deal = ${isDeal} " +
            " order by sbc.insert_time desc ")
    List<BuyCarEntity> getBuyCarList(@Param("id") Long id, @Param("isDeal") Integer isDeal);
}
