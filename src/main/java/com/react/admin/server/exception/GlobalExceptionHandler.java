package com.react.admin.server.exception;

import com.react.admin.server.domain.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.react.admin.server.constant.BaseConst.NOT_SUPPORT_MSG;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            HttpRequestMethodNotSupportedException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBase<String> handleHttpMediaTypeNotSupportedException(Exception e) {
        return ResponseBase.fail(NOT_SUPPORT_MSG);
    }

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBase<String> handleBizException(BizException e) {
        return ResponseBase.fail(e.getErrorMsg());
    }

}
