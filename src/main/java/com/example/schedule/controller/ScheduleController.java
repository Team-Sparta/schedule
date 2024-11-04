package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        return new ResponseEntity<>(scheduleService.findAllTasks(), HttpStatus.OK);
    }

}
