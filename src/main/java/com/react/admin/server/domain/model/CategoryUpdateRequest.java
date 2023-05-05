package com.react.admin.server.domain.model;

import lombok.Data;

@Data
public class CategoryUpdateRequest {
    private Integer categoryId;
    private String categoryName;
}
