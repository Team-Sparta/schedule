package com.example.schedule.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    SUCCESS(HttpStatus.OK, "Success."),
    SUCCESS_INSERT(HttpStatus.CREATED, "Data saved successfully."),
    SUCCESS_DELETE(HttpStatus.OK, "Data deleted successfully."),
    SUCCESS_UPDATE(HttpStatus.OK, "Data updated successfully.");

    private final HttpStatus httpStatus;
    private final String message;

}