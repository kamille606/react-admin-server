package com.react.admin.server.controller;

import com.react.admin.server.domain.ResponseBase;
import com.react.admin.server.domain.entity.User;
import com.react.admin.server.domain.model.UserLoginRequest;
import com.react.admin.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBase<User> userLogin(@RequestBody UserLoginRequest request) {
        return ResponseBase.success(userService.userLogin(request));
    }

}
