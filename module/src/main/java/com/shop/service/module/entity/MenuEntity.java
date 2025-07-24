package com.shop.service.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("shop_menu")
public class MenuEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "菜单名称不可以为空")
    private String name;
    @NotBlank(message = "菜单图表不可以为空")
    private String icon;

    private String url;

    private Integer level;

    private Integer isLink;

    private String remark;

    private Long pid;

}
