package com.react.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.react.admin.server.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select * from category_info where category_name = #{categoryName} and deleted = 0")
    Category selectByName(@Param("categoryName") String categoryName);

}
