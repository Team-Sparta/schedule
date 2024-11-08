package com.example.schedule.domain.repository.jdbc;

import com.example.schedule.common.exception.BaseException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.domain.dto.request.UserSignUpRequestDto;
import com.example.schedule.domain.dto.response.UserResponseDto;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.query.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) ->
            User.builder()
                    .id(rs.getLong("id"))
                    .username(rs.getString("username"))
                    .email(rs.getString("email"))
                    .password(rs.getString("email"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

    private static final RowMapper<String> PASSWORD_ROW_MAPPER = (rs, rowNum) ->
            rs.getString("password");


    @Override
    public User findByUserid(Long userid) {
        List<User> query = jdbcTemplate.query("SELECT id, username, email, created_at, updated_at FROM users WHERE id = ?", USER_ROW_MAPPER, userid);
        return query.stream().findAny().orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public User findByUsername(String username) {
        List<User> query = jdbcTemplate.query("SELECT id, username, email, password, created_at, updated_at FROM users WHERE username = ?", USER_ROW_MAPPER, username);
        return query.stream().findAny().orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public User findByUserEmail(String email) {
        List<User> query = jdbcTemplate.query("SELECT id, username, email, password, created_at, updated_at FROM users WHERE email = ?", USER_ROW_MAPPER, email);
        return query.stream().findAny().orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public Long register(UserSignUpRequestDto request) {

        try {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
            simpleJdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("username", request.getUsername());
            parameters.put("email", request.getEmail());
            parameters.put("password", request.getPassword());

            Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            return key.longValue();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new BaseException("", ErrorCode.SQL_DB_ERROR);
        }
    }


}
