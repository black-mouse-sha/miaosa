package com.shop.service.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("shop_address")
public class AddressEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long Id;

    private String province;

    private Long provinceId;

    private Long userId;

    private String city;

    private Long cityId;

    private String area;

    private Long areaId;

    private String name;

    private String phone;

    private String address;

    private Integer isDefault;

    private String postCode;
}
