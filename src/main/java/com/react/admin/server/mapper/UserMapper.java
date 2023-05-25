package com.react.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.react.admin.server.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user_info where username=#{username} and deleted=0")
    User selectOneByUserName(@Param("username") String username);

    @Select("select * from user_info where username=#{username} and password=#{password} and deleted=0")
    User selectOneByLogin(@Param("username") String username, @Param("password") String password);

}
