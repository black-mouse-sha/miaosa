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

@Data
@TableName("shop_buy_car")
public class BuyCarEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long goodsId;

    private Long userId;

    private Integer isDeal;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insertTime;

    private Integer buyCount;

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


    private String orderNo;

    private Long orderId;
}
