package com.react.admin.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("role_info")
public class Role {
    @TableId
    private Integer roleId;
    private String roleName;
    private String authName;
    private String menus;
    private Date authTime;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
