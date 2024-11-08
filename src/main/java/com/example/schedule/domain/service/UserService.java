package com.example.schedule.domain.service;

import com.example.schedule.domain.dto.request.UserSignUpRequestDto;
import com.example.schedule.domain.dto.response.TokenResponseDto;
import com.example.schedule.domain.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    TokenResponseDto register(@Valid @RequestBody UserSignUpRequestDto request);
    TokenResponseDto singIn(@Valid @RequestBody UserSignUpRequestDto request);
}
