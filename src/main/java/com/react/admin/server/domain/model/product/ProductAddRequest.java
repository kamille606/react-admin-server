package com.react.admin.server.domain.model.product;

import lombok.Data;

@Data
public class ProductAddRequest {
    private String productName;
    private String productDesc;
    private String productDetail;
    private Integer categoryId;
    private Integer categoryPid;
    private Integer productPrice;
    private String productImages;
}
