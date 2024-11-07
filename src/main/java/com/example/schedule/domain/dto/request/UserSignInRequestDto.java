package com.example.schedule.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class UserSignInRequestDto {
    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotNull(message = "email must not be null")
    @NotEmpty(message = "email must not be empty")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;
}