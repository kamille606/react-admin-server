package com.react.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.react.admin.server.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
