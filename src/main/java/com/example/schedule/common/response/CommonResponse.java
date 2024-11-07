package com.example.schedule.common.response;


import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.common.exception.code.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public record CommonResponse<T>(
        boolean success,
        String message,
        T result) {
    public static <T> ResponseEntity<CommonResponse<T>> success(SuccessCode successCode) {
        return ResponseEntity.status(successCode.getHttpStatus()).body(new CommonResponse<>(true, successCode.getMessage(), null));
    }

    public static <T> ResponseEntity<CommonResponse<T>> success(SuccessCode successCode, T data) {
        return ResponseEntity.status(successCode.getHttpStatus()).body(new CommonResponse<>(true, successCode.getMessage(), data));
    }

    public static <T> ResponseEntity<CommonResponse<T>> fail(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus()).body(new CommonResponse<>(false, errorCode.getMessage(), null));
    }

    public static <T> ResponseEntity<CommonResponse<T>> fail(ErrorCode errorCode, T data) {
        return ResponseEntity.status(errorCode.getHttpStatus()).body(new CommonResponse<>(false, errorCode.getMessage(), data));
    }

    public static <T> ResponseEntity<CommonResponse<T>> fail(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse<>(false, exception.getMessage(), null));
    }

}