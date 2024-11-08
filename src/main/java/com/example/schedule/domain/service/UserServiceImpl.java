package com.example.schedule.domain.service;

import com.example.schedule.api.provider.JwtTokenProvider;
import com.example.schedule.common.exception.BaseException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.domain.dto.request.UserSignUpRequestDto;
import com.example.schedule.domain.dto.response.TokenResponseDto;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.query.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponseDto register(@Valid UserSignUpRequestDto request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Long userId = userRepository.register(request);
        String token = jwtTokenProvider.createAccessToken(request.getEmail());
        return new TokenResponseDto(userId, token);
    }

    @Override
    public TokenResponseDto singIn(UserSignUpRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername());

        boolean valid = checkPassword(request.getPassword(), user.getPassword());

        if (!valid) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtTokenProvider.createAccessToken(request.getEmail());

        return new TokenResponseDto(user.getId(), token);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
