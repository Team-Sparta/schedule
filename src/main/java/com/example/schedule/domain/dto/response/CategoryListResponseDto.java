package com.example.schedule.domain.dto.response;

import java.util.List;

public record CategoryListResponseDto(
        List<CategoryResponseDto> categories
) {

}
