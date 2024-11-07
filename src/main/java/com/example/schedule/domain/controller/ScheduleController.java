package com.example.schedule.domain.controller;

import com.example.schedule.common.exception.code.SuccessCode;
import com.example.schedule.common.response.CommonResponse;
import com.example.schedule.domain.dto.response.ScheduleListResponse;
import com.example.schedule.domain.dto.request.ScheduleRequestDto;
import com.example.schedule.domain.dto.response.ScheduleResponseDto;
import com.example.schedule.domain.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/schedules")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<ScheduleListResponse>> findAllSchedules(@RequestParam Long userId, @RequestParam Long pageIndex, @RequestParam Integer pageSize) {
        return CommonResponse.success(SuccessCode.SUCCESS, scheduleService.findSchedules(userId, pageIndex, pageSize));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<ScheduleListResponse>> findAllSchedulesByUpdatedDate(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedDate) {
        return CommonResponse.success(SuccessCode.SUCCESS, scheduleService.findSchedulesByUpdatedDate(userId, updatedDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> findAllSchedulesByScheduleId(@PathVariable Long id, @RequestParam Long userId) {
        return CommonResponse.success(SuccessCode.SUCCESS, scheduleService.findSchedulesByScheduleId(userId, id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> createSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto request) {
        return CommonResponse.success(SuccessCode.SUCCESS_INSERT, scheduleService.saveSchedule(id, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto request) {
        return CommonResponse.success(SuccessCode.SUCCESS_INSERT, scheduleService.updateSchedule(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> updateScheduleContent(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto request) {
        return CommonResponse.success(SuccessCode.SUCCESS_UPDATE, scheduleService.updateScheduleContent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Long>> deleteSchedule(@PathVariable Long id, @RequestParam Long userId) {
        return CommonResponse.success(SuccessCode.SUCCESS_DELETE, scheduleService.deleteSchedule(id, userId));
    }

}
