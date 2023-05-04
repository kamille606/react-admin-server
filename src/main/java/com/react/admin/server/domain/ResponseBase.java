package com.react.admin.server.domain;

import static com.react.admin.server.constant.BaseConst.FAIL_MSG;
import static com.react.admin.server.constant.BaseConst.SUCCESS_MSG;

public record ResponseBase<T>(Boolean success, String message, T data) {

    public static <T> ResponseBase<T> success(T data) {
        return new ResponseBase<>(true, SUCCESS_MSG, data);
    }

    public static <T> ResponseBase<T> success(String message, T data) {
        return new ResponseBase<>(true, message, data);
    }

    public static <T> ResponseBase<T> fail() {
        return new ResponseBase<>(false, FAIL_MSG, null);
    }

    public static <T> ResponseBase<T> fail(String message) {
        return new ResponseBase<>(false, message, null);
    }

}
