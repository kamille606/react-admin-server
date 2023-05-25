package com.react.admin.server.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final String errorMessage;

    public BizException(String message) {
        this.errorMessage = message;
    }

}
