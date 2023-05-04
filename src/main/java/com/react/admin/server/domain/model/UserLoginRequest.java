package com.react.admin.server.domain.model;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
