package com.react.admin.server.domain.model.auth;

import lombok.Data;

@Data
public class RoleVo {
    private Integer roleId;
    private String roleName;
    private String authName;
    private String menus;
}
