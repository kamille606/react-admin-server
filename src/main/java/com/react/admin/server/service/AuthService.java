package com.react.admin.server.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.react.admin.server.domain.entity.Role;
import com.react.admin.server.domain.entity.User;
import com.react.admin.server.domain.model.auth.RoleAddRequest;
import com.react.admin.server.domain.model.auth.RoleUpdateRequest;
import com.react.admin.server.domain.model.auth.RoleVo;
import com.react.admin.server.domain.model.auth.UserAddRequest;
import com.react.admin.server.domain.model.auth.UserDeleteRequest;
import com.react.admin.server.domain.model.auth.UserLoginRequest;
import com.react.admin.server.domain.model.auth.UserUpdateRequest;
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

    public UserVo userLogin(UserLoginRequest request) {
        final String username = request.getUsername();
        final String password = request.getPassword();
        final User user = userMapper.selectOneByLogin(username, password);
        if (user == null) {
            throw new BizException("用户名或密码错误");
        }
        final UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
        final Role role = roleMapper.selectById(user.getRoleId());
        final RoleVo roleVo = BeanUtil.copyProperties(role, RoleVo.class);
        userVo.setRole(roleVo);
        return userVo;
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

    public boolean addUser(UserAddRequest request) {
        final String username = request.getUsername();
        final User user = userMapper.selectOneByUserName(username);
        if (user != null) {
            throw new BizException("用户名已存在");
        }
        User newUser = BeanUtil.copyProperties(request, User.class);
        if (userMapper.insert(newUser) == 0) {
            throw new BizException("添加失败");
        }
        return true;
    }

    public boolean deleteUser(UserDeleteRequest request) {
        if (userMapper.deleteById(request.getUserId()) == 0) {
            throw new BizException("删除失败");
        }
        return true;
    }

    public boolean updateUser(UserUpdateRequest request) {
        final User user = BeanUtil.copyProperties(request, User.class);
        if (userMapper.updateById(user) == 0) {
            throw new BizException("更新失败");
        }
        return true;
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
