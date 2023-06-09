package com.react.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.react.admin.server.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
