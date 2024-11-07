package com.example.schedule.domain.service;

import com.example.schedule.domain.dto.response.CategoryListResponseDto;
import com.example.schedule.domain.dto.response.CategoryResponseDto;
import com.example.schedule.domain.repository.query.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryListResponseDto getCategoryList() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryRepository.getCategoryList().stream()
                .map(category -> new CategoryResponseDto(category.getId(), category.getName()))
                .collect(Collectors.toList());
        return new CategoryListResponseDto(categoryResponseDtoList);
    }
}
