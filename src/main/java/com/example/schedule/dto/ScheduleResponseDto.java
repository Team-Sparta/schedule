package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;

public class ScheduleResponseDto {
    private Long id;
    private String content;


    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.content = schedule.getContent();
    }
}
