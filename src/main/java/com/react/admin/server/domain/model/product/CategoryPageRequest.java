package com.react.admin.server.domain.model.product;

import com.react.admin.server.domain.RequestPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryPageRequest extends RequestPage {
    private Integer categoryPid;
    private String categoryName;
}
