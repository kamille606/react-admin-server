package com.react.admin.server.domain.model.product;

import lombok.Data;

@Data
public class CategoryAddRequest {
    private Integer categoryPid;
    private String categoryName;
}
