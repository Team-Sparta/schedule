package com.example.schedule.domain.dto.response;

public record UserResponseDto(
        Long userId,
        String username,
        String email
) {

}
