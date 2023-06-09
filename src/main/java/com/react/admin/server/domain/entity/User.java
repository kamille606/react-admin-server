package com.react.admin.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_info")
public class User {
    @TableId
    private Integer userId;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String mobile;
    private Integer roleId;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
