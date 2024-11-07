package com.example.schedule.domain.dto.response;

import com.example.schedule.domain.entity.Category;
import com.example.schedule.domain.enums.Priority;
import com.example.schedule.domain.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record ScheduleResponseDto(
        Long scheduleId,
        String content,
        LocalDate dueDate,
        Priority priority,
        Status status,
        UserResponseDto user,
        Category category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
