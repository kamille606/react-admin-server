package com.react.admin.server.domain;

import lombok.Data;

@Data
public class RequestPage {
    private Integer current;
    private Integer pageSize;
}
