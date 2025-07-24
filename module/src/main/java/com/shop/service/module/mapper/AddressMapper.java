package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.AddressEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AddressMapper extends BaseMapper<AddressEntity> {

    @Select("<script>" +
            " select  " +
            "  id, " +
            "  `name`, " +
            "  province, " +
            "  province_id, " +
            "  user_id, " +
            "  city, " +
            "  city_id, " +
            "  area, " +
            "  area_id, " +
            "  phone, " +
            "  is_default, " +
            "  post_code, " +
            "  address " +
            " from shop_address " +
            " where user_id = #{userId} " +
            " order by is_default desc "+
            "</script>")
    Page<AddressEntity> getAddressListForPage(Page p, @Param("userId") String userId);
}
