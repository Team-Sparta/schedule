package com.example.schedule.common.exception;


import com.example.schedule.common.exception.code.ErrorCode;

public class InvalidParamException extends BaseException {

    public InvalidParamException() {
        super(ErrorCode.COMMON_INVALID_PARAM);
    }

    public InvalidParamException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidParamException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAM);
    }

    public InvalidParamException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}