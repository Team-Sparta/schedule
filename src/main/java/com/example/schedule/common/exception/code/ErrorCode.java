package com.example.schedule.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation failed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error has occurred."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "User not found."),
    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "Schedule not found."),
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "Category not found."),

    INVALID_PRIORITY_TYPE(HttpStatus.BAD_REQUEST, "Invalid Priority Value");


    private final HttpStatus httpStatus;
    private final String message;
}