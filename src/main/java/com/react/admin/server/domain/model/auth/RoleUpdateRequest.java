package com.react.admin.server.domain.model.auth;

import lombok.Data;

@Data
public class RoleUpdateRequest {
    private Integer roleId;
    private String menus;
}
