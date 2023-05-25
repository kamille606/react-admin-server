package com.react.admin.server.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import static com.react.admin.server.constant.BaseConst.SUCCESS_MSG;

public record ResponsePage<T>(Boolean success, String message, Long total, List<T> data) {

    public static <T> ResponsePage<T> success(List<T> data) {
        return success(data, data.size());
    }

    public static <T> ResponsePage<T> success(Page<T> data) {
        return success(data.getRecords(), data.getTotal());
    }

    public static <T> ResponsePage<T> success(List<T> data, long total) {
        return new ResponsePage<>(true, SUCCESS_MSG, total, data);
    }

}
