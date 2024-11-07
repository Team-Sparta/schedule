package com.example.schedule.domain.dto.request;

import com.example.schedule.domain.enums.Priority;
import com.example.schedule.domain.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {

    @NotNull(message = "userId must not be null")
    private Long userId;

    @NotNull(message = "content must not be null")
    @NotEmpty(message = "content must not be empty")
    private String content;

    private Long categoryId;

    private LocalDate dueDate;

    @Nullable
    private Priority priority;

    @Nullable
    private Status status;
}