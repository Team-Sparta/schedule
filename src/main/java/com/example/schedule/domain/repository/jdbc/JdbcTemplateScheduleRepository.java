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
            new Schedule(
                    rs.getLong("id"),
                    rs.getString("content"),
                    rs.getObject("due_date", LocalDate.class),
                    Priority.valueOf(rs.getString("priority").toUpperCase()),
                    Status.valueOf(rs.getString("status").toUpperCase()),
                    rs.getLong("category_id") != 0 ? new Category(rs.getLong("category_id"), rs.getString("category_name")) : null,
                    new User(rs.getLong("user_id"), rs.getString("username"), rs.getString("email"), rs.getObject("created_at", LocalDateTime.class),
                            rs.getObject("updated_at", LocalDateTime.class)),
                    rs.getObject("created_at", LocalDateTime.class),
                    rs.getObject("updated_at", LocalDateTime.class)
            );


    // Helper method to reduce redundant query logic
    private List<Schedule> findSchedulesByUserIdQuery(String additionalWhereClause, Object... params) {
        String query = "SELECT S.*, U.id AS user_id, U.username, U.email, " +
                "C.id AS category_id, C.name AS category_name " +
                "FROM Schedules S " +
                "INNER JOIN Users U ON S.user_id = U.id " +
                "LEFT JOIN Categories C ON S.category_id = C.id " +
                "WHERE user_id = ? " + additionalWhereClause;
        return jdbcTemplate.query(query, SCHEDULE_ROW_MAPPER, params);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
        simpleJdbcInsert.withTableName("Schedules").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("content", schedule.getContent());
        params.put("user_id", schedule.getUser().getId());
        params.put("category_id", schedule.getCategory().getId());
        params.put("due_date", schedule.getDueDate());
        params.put("priority", schedule.getPriority());
        params.put("status", schedule.getStatus());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new Schedule(key.longValue(), schedule.getContent(), schedule.getDueDate(), schedule.getPriority(), schedule.getStatus(), schedule.getCategory(), new User(schedule.getUser().getId(), schedule.getUser().getUsername(), schedule.getUser().getEmail()));
    }

    @Override
    public int updateScheduleContent(Long scheduleId, Long userId, String content) {
        return jdbcTemplate.update("UPDATE Schedules SET content = ? WHERE id = ? AND user_id = ?", content, scheduleId, userId);
    }

    @Override
    public int updateSchedule(Long scheduleId, Long userId, String content, Long categoryId, LocalDate dueDate, Priority priority, Status status) {
        System.out.println(priority.name());
        return jdbcTemplate.update("UPDATE Schedules SET content = ?, category_id = ?, due_date = ?, priority = ?, status = ? WHERE id = ? AND user_id = ?",
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

        return findSchedulesByUserIdQuery(additionalWhereClause, userId, scheduleId).stream().findAny().orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_SCHEDULE));

    }

    @Override
    public List<Schedule> findSchedulesByUpdatedDate(Long userId, LocalDateTime updatedDate) {

        String additionalWhereClause = "AND S.updated_at >= ?";

        return findSchedulesByUserIdQuery(additionalWhereClause, userId, updatedDate);

    }

    @Override
    public int deleteSchedule(Long userId, Long scheduleId) {
        return jdbcTemplate.update("DELETE FROM Schedules WHERE id = ? AND user_id = ?", scheduleId, userId);
    }
}







