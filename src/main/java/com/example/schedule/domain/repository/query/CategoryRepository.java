package com.example.schedule.domain.repository.query;

import com.example.schedule.domain.entity.Category;

import java.util.List;

public interface CategoryRepository {
    Category findCategoryById(Long id);
    List<Category> getCategoryList();
}
