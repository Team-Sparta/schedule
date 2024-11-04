package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAllSchedules(Long userId);
    List<Schedule> findAllSchedulesByUpdatedDate(Long userId, Date updatedDate);
}
