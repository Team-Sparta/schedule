package com.example.schedule.domain.repository.query;

import com.example.schedule.domain.dto.response.ScheduleResponseDto;
import com.example.schedule.domain.entity.Schedule;
import com.example.schedule.domain.enums.Priority;
import com.example.schedule.domain.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
    int updateScheduleContent(Long scheduleId, Long userId,  String content);
    int updateSchedule(Long scheduleId, Long userId, String content, Long categoryId, LocalDate dueDate, Priority priority, Status status);
    List<Schedule> findSchedules(Long userId, Long pageIndex, Integer pageSize);
    Schedule findSchedulesByScheduleId(Long userId, Long scheduleId);
    List<Schedule> findSchedulesByUpdatedDate(Long userId, LocalDateTime updatedDate);
    int deleteSchedule(Long userId, Long scheduleId);
}
