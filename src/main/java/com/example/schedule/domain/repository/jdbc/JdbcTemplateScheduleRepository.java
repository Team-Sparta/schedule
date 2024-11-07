package com.example.schedule.domain.repository.jdbc;

import com.example.schedule.common.exception.BaseException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.domain.entity.Category;
import com.example.schedule.domain.entity.Schedule;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.enums.Priority;
import com.example.schedule.domain.enums.Status;
import com.example.schedule.domain.repository.query.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // General RowMapper to avoid repetition
    private static final RowMapper<Schedule> SCHEDULE_ROW_MAPPER = (rs, rowNum) ->
            Schedule.builder()
                    .id(rs.getLong("id"))
                    .content(rs.getString("content"))
                    .dueDate(rs.getObject("due_date", LocalDate.class))
                    .priority(Priority.valueOf(rs.getString("priority").toUpperCase()))
                    .status(Status.valueOf(rs.getString("status").toUpperCase()))
                    .category(rs.getLong("category_id") != 0 ? new Category(rs.getLong("category_id"), rs.getString("category_name")) : null)
                    .user(User.builder().id(rs.getLong("user_id")).username(rs.getString("username")).email("email").build())
                    .createdAt(rs.getObject("created_at", LocalDateTime.class))
                    .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                    .build();


    private static final String BASE_SCHEDULE_QUERY =
            "SELECT S.id, S.content, S.due_date, S.priority, S.status, " +
                    "S.category_id, S.user_id, S.created_at, S.updated_at, " +
                    "U.username, U.email, " +
                    "C.name AS category_name " +
                    "FROM schedules S " +
                    "INNER JOIN Users U ON S.user_id = U.id " +
                    "LEFT JOIN Categories C ON S.category_id = C.id " +
                    "WHERE S.user_id = ? ";

    private List<Schedule> findSchedulesByUserIdQuery(String additionalWhereClause, Object... params) {
        return jdbcTemplate.query(BASE_SCHEDULE_QUERY + additionalWhereClause, SCHEDULE_ROW_MAPPER, params);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
        simpleJdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("content", schedule.getContent());
        params.put("user_id", schedule.getUser().getId());
        params.put("category_id", schedule.getCategory().getId());
        params.put("due_date", schedule.getDueDate());
        params.put("priority", schedule.getPriority());
        params.put("status", schedule.getStatus());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return Schedule.builder()
                .id(key.longValue())
                .content(schedule.getContent())
                .dueDate(schedule.getDueDate())
                .priority(schedule.getPriority())
                .status(schedule.getStatus())
                .category(schedule.getCategory())
                .user(schedule.getUser())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }

    @Override
    public int updateScheduleContent(Long scheduleId, Long userId, String content) {
        return jdbcTemplate.update("UPDATE schedules SET content = ? WHERE id = ? AND user_id = ?", content, scheduleId, userId);
    }

    @Override
    public int updateSchedule(Long scheduleId, Long userId, String content, Long categoryId, LocalDate dueDate, Priority priority, Status status) {
        System.out.println(priority.name());
        return jdbcTemplate.update("UPDATE schedules SET content = ?, category_id = ?, due_date = ?, priority = ?, status = ? WHERE id = ? AND user_id = ?",
                content, categoryId, dueDate, priority.name(), status.name(), scheduleId, userId);
    }

    @Override
    public List<Schedule> findSchedules(Long userId, Long pageIndex, Integer pageSize) {
        Long offset = (pageIndex - 1) * pageSize;
        String additionalWhereClause = "LIMIT ? OFFSET ?";

        return findSchedulesByUserIdQuery(additionalWhereClause, userId, pageSize, offset);
    }

    @Override
    public Schedule findSchedulesByScheduleId(Long userId, Long scheduleId) {

        String additionalWhereClause = "AND S.id = ?";


        Schedule schedule = findSchedulesByUserIdQuery(additionalWhereClause, userId, scheduleId).stream().findAny().orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_SCHEDULE));
        System.out.println("test2");
        return schedule;

    }

    @Override
    public List<Schedule> findSchedulesByUpdatedDate(Long userId, LocalDateTime updatedDate) {

        String additionalWhereClause = "AND S.updated_at >= ?";

        return findSchedulesByUserIdQuery(additionalWhereClause, userId, updatedDate);

    }

    @Override
    public int deleteSchedule(Long userId, Long scheduleId) {
        return jdbcTemplate.update("DELETE FROM schedules WHERE id = ? AND user_id = ?", scheduleId, userId);
    }
}







