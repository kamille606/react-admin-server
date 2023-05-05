package com.react.admin.server.domain.model;

import lombok.Data;

@Data
public class CategoryAddRequest {
    private Integer parentId;
    private String categoryName;
}
