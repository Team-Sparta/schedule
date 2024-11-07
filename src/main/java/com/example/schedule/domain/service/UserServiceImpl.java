package com.example.schedule.domain.service;

import com.example.schedule.domain.dto.request.UserSignInRequestDto;
import com.example.schedule.domain.dto.response.UserResponseDto;
import com.example.schedule.domain.repository.query.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto register(@Valid UserSignInRequestDto request) {
        return userRepository.register(request);
    }

}
