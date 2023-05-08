package com.react.admin.server.domain.model.product;

import lombok.Data;

@Data
public class CategoryUpdateRequest {
    private Integer categoryId;
    private String categoryName;
}
