package com.example.schedule.domain.repository.query;

import com.example.schedule.domain.dto.response.ScheduleResponseDto;
import com.example.schedule.domain.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
    int updateScheduleContent(Long scheduleId, Long userId,  String content);
    List<Schedule> findSchedules(Long userId, Long pageIndex, Integer pageSize);
    Schedule findAllSchedulesByScheduleId(Long userId, Long scheduleId);
    List<Schedule> findAllSchedulesByUpdatedDate(Long userId, LocalDateTime updatedDate);
    int deleteSchedule(Long userId, Long scheduleId);
}
