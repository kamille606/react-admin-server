package com.react.admin.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("category_info")
public class Category {
    @TableId
    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
