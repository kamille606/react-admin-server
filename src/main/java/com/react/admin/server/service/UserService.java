package com.react.admin.server.service;

import com.react.admin.server.domain.entity.User;
import com.react.admin.server.domain.model.UserLoginRequest;
import com.react.admin.server.exception.BizException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User userLogin(UserLoginRequest request) {
        if ("admin".equals(request.getUsername()) && "12345".equals(request.getPassword())) {
            User user = new User();
            user.setId(1);
            user.setNickname("nick");
            user.setEmail("a@qq.com");
            return user;
        }
        throw new BizException("登录失败,用户名或密码不正确");
    }

}
