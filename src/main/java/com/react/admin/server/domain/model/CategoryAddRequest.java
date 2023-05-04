package com.react.admin.server.domain.model;

import lombok.Data;

@Data
public class CategoryAddRequest {
    private String categoryName;
    private Integer parentId;
}
