package com.react.admin.server.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    protected String errorMsg;

    public BizException(String message) {
        this.errorMsg = message;
    }

}
