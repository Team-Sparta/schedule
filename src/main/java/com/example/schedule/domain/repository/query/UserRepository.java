package com.example.schedule.domain.repository.query;

import com.example.schedule.domain.dto.request.UserSignInRequestDto;
import com.example.schedule.domain.dto.response.UserResponseDto;
import com.example.schedule.domain.entity.User;

public interface UserRepository {
    User findByUserid(Long userid);

    UserResponseDto register(UserSignInRequestDto request);
}
