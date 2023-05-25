package com.react.admin.server.domain.model.auth;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private Integer userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String mobile;
    private Integer roleId;
}
