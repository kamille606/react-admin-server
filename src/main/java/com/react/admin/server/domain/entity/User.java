package com.react.admin.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class User {
    private Integer id;
    private String nickname;
    private String email;
}
