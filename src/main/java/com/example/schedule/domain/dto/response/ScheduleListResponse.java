package com.example.schedule.domain.dto.response;


import java.util.List;

public record ScheduleListResponse(
        List<ScheduleResponseDto> schedules
) {
}
