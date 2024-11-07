package com.example.schedule.domain.repository.jdbc;

import com.example.schedule.common.exception.BaseException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.domain.entity.Category;
import com.example.schedule.domain.repository.query.CategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateCategoryRepository implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, rowNum) ->
            new Category(
                    rs.getLong("id"),
                    rs.getString("name")
            );

    @Override
    public Category findCategoryById(Long id) {
        List<Category> query = jdbcTemplate.query("SELECT * FROM Categories WHERE id = ?", CATEGORY_ROW_MAPPER, id);
        return query.stream().findAny().orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CATEGORY));
    }

    @Override
    public List<Category> getCategoryList() {
        String query = "SELECT * FROM Categories";
        return jdbcTemplate.query(query, CATEGORY_ROW_MAPPER);
    }

}
