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
@TableName("shop_order")
public class OrderEntity  implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String orderNo;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insertTime;

    private Long goodsId;

    private Long teamId;

    private Long buyCarId;

    /**
     * 订单类型:
     * 1.普通订单，
     * 2.购物车订单，
     * 3.拼团订单，
     * 4.秒杀订单
     */
    private Integer type;

    private Long userId;

    private Long addressId;

    private String address;

    private String city;

    private String province;

    private String area;

    private String postCode;

    private String phone;

    private String name;

    /**
     * 状态：
     * 0.待支付，
     * 1.已支付，
     * 2.已发货，
     * 3.确认收货，
     * 4.申请退款，
     * 5.退款完成
     */
    private Integer status;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsDiscount;

    private Integer count;

    private String remark;

    private Integer activityDiscount;

    private String activityName;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fallbackTime;

    private String fallbackReason;

    private String fallbackImg;

    private String expressNo;

    private Double pay;

    @TableField(exist = false)
    private String password;



}
