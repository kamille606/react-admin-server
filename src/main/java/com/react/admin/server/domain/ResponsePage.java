package com.react.admin.server.domain;

import java.util.List;

import static com.react.admin.server.constant.BaseConst.FAIL_MSG;
import static com.react.admin.server.constant.BaseConst.SUCCESS_MSG;

public record ResponsePage<T>(Boolean success, String message, Long total, List<T> data) {

    public static <T> ResponsePage<T> success(List<T> data) {
        return success(data, data.size());
    }

    public static <T> ResponsePage<T> success(List<T> data, long total) {
        return new ResponsePage<>(true, SUCCESS_MSG, total, data);
    }

    public static <T> ResponsePage<T> fail() {
        return new ResponsePage<>(false, FAIL_MSG, null, null);
    }

}
