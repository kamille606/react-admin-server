package com.react.admin.server.controller;

import com.react.admin.server.domain.ResponseBase;
import com.react.admin.server.domain.ResponsePage;
import com.react.admin.server.domain.entity.Role;
import com.react.admin.server.domain.entity.User;
import com.react.admin.server.domain.model.auth.RoleAddRequest;
import com.react.admin.server.domain.model.auth.RoleUpdateRequest;
import com.react.admin.server.domain.model.auth.UserLoginRequest;
import com.react.admin.server.domain.model.auth.UserVo;
import com.react.admin.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseBase<User> userLogin(@RequestBody UserLoginRequest request) {
        return ResponseBase.success(authService.userLogin(request));
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ResponsePage<UserVo> queryUserList() {
        return ResponsePage.success(authService.queryUserList());
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public ResponsePage<Role> queryRoleList() {
        return ResponsePage.success(authService.queryRoleList());
    }

    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public ResponseBase<Boolean> addRole(@RequestBody RoleAddRequest request) {
        return ResponseBase.success(authService.addRole(request));
    }

    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public ResponseBase<Boolean> updateRole(@RequestBody RoleUpdateRequest request) {
        return ResponseBase.success(authService.updateRole(request));
    }

}
