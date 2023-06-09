package com.react.admin.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("product_info")
public class Product {
    @TableId
    private Integer productId;
    private String productName;
    private String productDesc;
    private String productDetail;
    private Integer categoryId;
    private Integer categoryPid;
    private Integer productPrice;
    private String productImages;
    /**
     * 商品状态1已上架2未上架
     */
    private Integer productStatus;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
}
