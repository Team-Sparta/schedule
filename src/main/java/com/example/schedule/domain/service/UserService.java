package com.example.schedule.domain.service;

import com.example.schedule.domain.dto.request.UserSignInRequestDto;
import com.example.schedule.domain.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    UserResponseDto register(@Valid @RequestBody UserSignInRequestDto request);
}
