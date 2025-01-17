package com.example.schedule.domain.service;

import com.example.schedule.domain.dto.response.ScheduleListResponse;
import com.example.schedule.domain.dto.request.ScheduleRequestDto;
import com.example.schedule.domain.dto.response.ScheduleResponseDto;

import java.time.LocalDateTime;


public interface ScheduleService {
    ScheduleResponseDto saveSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto updateScheduleContent(Long scheduleId, ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto);

    ScheduleListResponse findSchedules(Long userId, Long pageIndex, Integer pageSize);

    ScheduleListResponse findSchedulesByUpdatedDate(Long userId, LocalDateTime updatedDate);

    ScheduleResponseDto findSchedulesByScheduleId(Long userId, Long scheduleId);

    Long deleteSchedule(Long userId, Long scheduleId);
}
