package com.example.schedule.domain.service;

import com.example.schedule.common.exception.BaseException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.domain.dto.response.ScheduleListResponse;
import com.example.schedule.domain.dto.request.ScheduleRequestDto;
import com.example.schedule.domain.dto.response.ScheduleResponseDto;
import com.example.schedule.domain.dto.response.UserResponseDto;
import com.example.schedule.domain.entity.Category;
import com.example.schedule.domain.entity.Schedule;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.query.CategoryRepository;
import com.example.schedule.domain.repository.query.ScheduleRepository;
import com.example.schedule.domain.repository.query.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public ScheduleResponseDto saveSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        User user = userRepository.findByUserid(scheduleRequestDto.getUserId());

        Category category = categoryRepository.findCategoryById(id);


        Schedule schedule = scheduleRepository.saveSchedule(
                new Schedule(
                        scheduleRequestDto.getContent(),
                        scheduleRequestDto.getDueDate(),
                        scheduleRequestDto.getPriority(),
                        scheduleRequestDto.getStatus(),
                        category,
                        user
                ));
        return new ScheduleResponseDto(schedule.getId(), schedule.getContent(), schedule.getDueDate(), schedule.getPriority(), schedule.getStatus(), new UserResponseDto(schedule.getUser().getId(), schedule.getUser().getUsername(), schedule.getUser().getEmail()), schedule.getCategory(), schedule.getCreatedAt(), schedule.getUpdatedAt());

    }

    @Transactional
    @Override
    public ScheduleResponseDto updateScheduleContent(Long id, ScheduleRequestDto scheduleRequestDto) {

        int updatedRow = scheduleRepository.updateScheduleContent(id, scheduleRequestDto.getUserId(), scheduleRequestDto.getContent());

        if (updatedRow == 0) {
            throw new BaseException(ErrorCode.NOT_FOUND_SCHEDULE);
        }

        Schedule schedule = scheduleRepository.findAllSchedulesByScheduleId(scheduleRequestDto.getUserId(), id);
        return new ScheduleResponseDto(schedule.getId(), schedule.getContent(), schedule.getDueDate(), schedule.getPriority(), schedule.getStatus(), new UserResponseDto(schedule.getUser().getId(), schedule.getUser().getUsername(), schedule.getUser().getEmail()), schedule.getCategory(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    @Override
    public ScheduleListResponse findSchedules(Long userId, Long pageIndex, Integer pageSize) {
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleRepository.findSchedules(userId, pageIndex, pageSize).stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getContent(),
                        schedule.getDueDate(),
                        schedule.getPriority(),
                        schedule.getStatus(),
                        new UserResponseDto(
                                schedule.getUser().getId(),
                                schedule.getUser().getUsername(),
                                schedule.getUser().getEmail()
                        ),
                        schedule.getCategory(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());


        return new ScheduleListResponse(scheduleResponseDtos);
    }

    @Override
    public ScheduleListResponse findAllSchedulesByUpdatedDate(Long userId, LocalDateTime updatedDate) {
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleRepository.findAllSchedulesByUpdatedDate(userId, updatedDate).stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getContent(),
                        schedule.getDueDate(),
                        schedule.getPriority(),
                        schedule.getStatus(),
                        new UserResponseDto(
                                schedule.getUser().getId(),
                                schedule.getUser().getUsername(),
                                schedule.getUser().getEmail()
                        ),
                        schedule.getCategory(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        return new ScheduleListResponse(scheduleResponseDtos);
    }

    @Override
    public ScheduleResponseDto findAllSchedulesByScheduleId(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findAllSchedulesByScheduleId(userId, scheduleId);
        return new ScheduleResponseDto(schedule.getId(), schedule.getContent(), schedule.getDueDate(), schedule.getPriority(), schedule.getStatus(), new UserResponseDto(schedule.getUser().getId(), schedule.getUser().getUsername(), schedule.getUser().getEmail()), schedule.getCategory(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    @Override
    public Long deleteSchedule(Long userId, Long scheduleId) {
        int deletedRow = scheduleRepository.deleteSchedule(userId, scheduleId);
        if (deletedRow == 0) {
            throw new BaseException(ErrorCode.NOT_FOUND_SCHEDULE);
        }
        return scheduleId;
    }
}
