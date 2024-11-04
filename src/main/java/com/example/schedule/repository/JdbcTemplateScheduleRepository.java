package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private static final RowMapper<Schedule> TASK_ROW_MAPPER = (rs, rowNum) ->
            new Schedule(

            );

    @Override
    public List<Schedule> findAllSchedules(Long userId) {
        return jdbcTemplate.query("SELECT * FROM Schedules WHERE user_id = ?", TASK_ROW_MAPPER, userId);
    }

    @Override
    public List<Schedule> findAllSchedulesByUpdatedDate(Long userId, Date updatedDate) {
        return List.of();
    }


}
