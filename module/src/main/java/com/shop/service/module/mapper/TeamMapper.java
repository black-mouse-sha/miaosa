package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.TeamEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeamMapper extends BaseMapper<TeamEntity> {

    @Select("<script>" +
            "select   " +
            " st.id,  " +
            " st.goods_id,  " +
            " sg.name as goods_name,  " +
            " sg.price as goods_price,  " +
            " sg.discount as goods_discount,  " +
            " sg.description as goods_description,  " +
            " sg.remark as goods_remark,  " +
            " sg.count as goods_count,  " +
            " sg.logo as goods_logo,  " +
            " st.insert_time,  " +
            " st.size,  " +
            " st.end_time,  " +
            " st.status,  " +
            " st.is_on_sale,  " +
            " st.remark,  " +
            " st.name,  " +
            " st.team_discount ," +
            " st.has_member, " +
            " st.type " +
            "from shop_team st  " +
            "left join shop_goods sg on st.goods_id = sg.id   " +

            " where 1 = 1  " +
            " <if test='name !=null and name != \"\" '> " +
            "  and st.name like '%${name}%' " +
            " </if> " +
            " <if test='isOnSale !=null '> " +
            "  and st.is_on_sale = ${isOnSale} " +
            " </if> " +
            " <if test='type !=null '> " +
            "  and st.type = ${type} " +
            " </if> " +
            " order by st.insert_time desc " +
            "</script>")
    Page<TeamEntity> getTeamListForPage(Page<TeamEntity> page, @Param("name") String name, @Param("isOnSale") Integer isOnSale, @Param("type") Integer type);

    @Select("<script>" +
            "select   " +
            " st.id,  " +
            " st.goods_id,  " +
            " sg.name as goods_name,  " +
            " sg.price as goods_price,  " +
            " sg.discount as goods_discount,  " +
            " sg.description as goods_description,  " +
            " sg.remark as goods_remark,  " +
            " sg.count as goods_count,  " +
            " sg.logo as goods_logo,  " +
            " st.insert_time,  " +
            " st.size,  " +
            " st.end_time,  " +
            " st.status,  " +
            " st.is_on_sale,  " +
            " st.remark,  " +
            " st.name,  " +
            " st.team_discount ," +
            " st.has_member, " +
            " st.type " +
            "from shop_team st  " +
            "left join shop_goods sg on st.goods_id = sg.id " +
            "left join shop_team_user stu on st.id = stu.team_id  " +

            " where 1 = 1 and st.is_on_sale=1 and stu.user_id = #{userId} " +

            " <if test='type !=null '> " +
            "  and st.type = ${type} " +
            " </if> " +
            " order by st.insert_time desc " +
            "</script>")
    Page<TeamEntity> getMyTeamListForPage(Page<TeamEntity> page,@Param("type") Integer type,@Param("userId") Long userId);

}
