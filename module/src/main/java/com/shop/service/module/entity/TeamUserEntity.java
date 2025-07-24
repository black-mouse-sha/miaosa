package com.shop.service.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("shop_team_user")
public class TeamUserEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long teamId;

    private Long userId;

    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private int isDeal;

    @TableField(exist = false)
    private String nickname;

    @TableField(exist = false)
    private String face;


}
