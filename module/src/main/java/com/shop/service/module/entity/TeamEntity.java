package com.shop.service.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("shop_team")
public class TeamEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;


    private Integer size;

    private String name;

    private Long goodsId;

    @TableField(exist = false)
    private List<UserEntity> teamMembers;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insertTime;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Integer hasMember;

    /**
     * 活动状态
     * 0：拼团中
     * 1：活动超时
     * 2：活动正常结束
     */
    private Integer status;

    /**
     * 活动状态
     * 0：下架
     * 1：上架
     */
    private Integer isOnSale;

    private Integer teamDiscount;

    private String remark;

    private Integer type;

    @TableField(exist = false)
    private String goodsName;

    @TableField(exist = false)
    private Double goodsPrice;

    @TableField(exist = false)
    private Integer goodsDiscount;

    @TableField(exist = false)
    private String goodsDescription;

    @TableField(exist = false)
    private String goodsRemark;

    @TableField(exist = false)
    private Integer goodsCount;

    @TableField(exist = false)
    private String goodsLogo;

}
