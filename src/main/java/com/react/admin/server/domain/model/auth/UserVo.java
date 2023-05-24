package com.react.admin.server.domain.model.auth;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Integer userId;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    private Integer roleId;
    private String roleName;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
