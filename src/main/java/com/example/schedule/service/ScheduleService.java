package com.example.schedule.service;

import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDto> findAllTasks();
}
