package com.react.admin.server.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.react.admin.server.domain.entity.Role;
import com.react.admin.server.domain.entity.User;
import com.react.admin.server.domain.model.auth.RoleAddRequest;
import com.react.admin.server.domain.model.auth.RoleUpdateRequest;
import com.react.admin.server.domain.model.auth.UserLoginRequest;
import com.react.admin.server.domain.model.auth.UserVo;
import com.react.admin.server.exception.BizException;
import com.react.admin.server.mapper.RoleMapper;
import com.react.admin.server.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.react.admin.server.constant.BaseConst.EMPTY;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public User userLogin(UserLoginRequest request) {
        if ("admin".equals(request.getUsername()) && "12345".equals(request.getPassword())) {
            User user = new User();
            user.setUserId(1);
            user.setNickname("nick");
            user.setEmail("a@qq.com");
            return user;
        }
        throw new BizException("登录失败,用户名或密码不正确");
    }

    public List<UserVo> queryUserList() {
        final List<User> userList = userMapper.selectList(null);
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>(0);
        }
        final List<Integer> roleIdList = userList.stream().map(User::getRoleId).collect(Collectors.toList());
        final Map<Integer, String> roleMap = roleMapper.selectBatchIds(roleIdList)
                .stream().collect(Collectors.toMap(Role::getRoleId, Role::getRoleName));

        final List<UserVo> userVoList = BeanUtil.copyToList(userList, UserVo.class);
        userVoList.forEach(userVo -> userVo.setRoleName(roleMap.get(userVo.getRoleId())));
        return userVoList;
    }

    public List<Role> queryRoleList() {
        return roleMapper.selectList(null);
    }

    public boolean addRole(RoleAddRequest request) {
        Role role = new Role();
        role.setRoleName(request.getRoleName());
        role.setAuthName("admin");
        role.setMenus(EMPTY);
        if (roleMapper.insert(role) == 0) {
            throw new BizException("添加失败");
        }
        return true;
    }

    public boolean updateRole(RoleUpdateRequest request) {
        final Integer roleId = request.getRoleId();
        final Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new BizException("角色不存在");
        }
        role.setMenus(request.getMenus());
        if (roleMapper.updateById(role) == 0) {
            throw new BizException("更新失败");
        }
        return true;
    }

}
