package com.example.schedule.domain.service;

import com.example.schedule.domain.dto.response.CategoryListResponseDto;
import com.example.schedule.domain.dto.response.CategoryResponseDto;
import com.example.schedule.domain.entity.Category;
import com.example.schedule.domain.repository.query.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryListResponseDto getCategoryList() {

        List<Category> categoryList = categoryRepository.getCategoryList();
        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryResponseDtoList.add(new CategoryResponseDto(
                    category.getId(),
                    category.getName()
            ));
        }
        return new CategoryListResponseDto(categoryResponseDtoList);
    }
}
