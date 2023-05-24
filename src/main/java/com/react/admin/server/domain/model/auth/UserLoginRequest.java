package com.react.admin.server.domain.model.auth;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
