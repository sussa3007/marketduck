package com.devgang.marketduck.api.openapi.category.dto;

import com.devgang.marketduck.constant.CategoryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto {

    private Long categoryId;

    private String categoryName;

    private CategoryType categoryType;

    @QueryProjection
    public CategoryResponseDto(Long categoryId, String categoryName, CategoryType categoryType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public CategoryResponseDto() {
    }
    public static CategoryResponseDto of(Long categoryId, String categoryName, CategoryType categoryType) {
        return CategoryResponseDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryType(categoryType)
                .build();
    }

}
