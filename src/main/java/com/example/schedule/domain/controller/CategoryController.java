package com.example.schedule.domain.controller;

import com.example.schedule.common.exception.code.SuccessCode;
import com.example.schedule.common.response.CommonResponse;
import com.example.schedule.domain.dto.response.CategoryListResponseDto;
import com.example.schedule.domain.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CommonResponse<CategoryListResponseDto>> findAllCategories() {
        return CommonResponse.success(SuccessCode.SUCCESS, categoryService.getCategoryList());
    }



}
