package com.react.admin.server.domain.model.product;

import com.react.admin.server.domain.RequestPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPageRequest extends RequestPage {
    private String productName;
    private String productDesc;
}
