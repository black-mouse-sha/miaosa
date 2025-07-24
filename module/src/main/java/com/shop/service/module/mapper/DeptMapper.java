package com.shop.service.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.service.module.entity.DeptEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DeptMapper extends BaseMapper<DeptEntity> {
    @Select("<script>"+
            "select "+
            " id, "+
            " name, "+
            " remark, "+
            " description "+
            "from shop_dept "+
            "where 1 = 1 "+
            "<if test='name != null and name != \"\"'>"+
            "and name like '%${name}%'"+
            "</if>"+
            "</script>")
    Page<DeptEntity> getDeptListForPage(Page<DeptEntity>page, @Param("name") String name);



}
