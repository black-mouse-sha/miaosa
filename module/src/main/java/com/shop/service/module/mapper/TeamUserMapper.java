package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.service.module.entity.TeamUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeamUserMapper extends BaseMapper<TeamUserEntity> {

    @Select("<script>" +
            "SELECT " +
            " sa.username," +
            " sa.phone, " +
            " sa.nickname, " +
            " sa.face, " +
            " if (so.`status` = 0,0,1 ) as is_deal, " +
            " stu.id, " +
            " stu.team_id, " +
            " stu.user_id " +
            " from shop_admin sa " +
            " left join shop_order so on sa.id = so.user_id " +
            " left join shop_team_user stu on stu.user_id = sa.id " +
            " where stu.team_id = #{teamId} and so.team_id = #{teamId} " +
            "</script>")
    List<TeamUserEntity> getTeamUserList(@Param("teamId") Long teamId);
}