package com.example.schedule.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleResponseDto> findAllTasks() {
        return List.of();
    }
}
