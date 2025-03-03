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

    // 카테고리 인기도(사용자 수)
    private Long popularityCount;

    @QueryProjection
    public CategoryResponseDto(Long categoryId, String categoryName, CategoryType categoryType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.popularityCount = 0L; // 기본값 설정
    }

    @QueryProjection
    public CategoryResponseDto(Long categoryId, String categoryName, CategoryType categoryType, Long popularityCount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.popularityCount = popularityCount;
    }

    public CategoryResponseDto() {
    }

    public static CategoryResponseDto of(Long categoryId, String categoryName, CategoryType categoryType) {
        return CategoryResponseDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryType(categoryType)
                .popularityCount(0L) // 기본값 설정
                .build();
    }

    public static CategoryResponseDto of(Long categoryId, String categoryName, CategoryType categoryType,
            Long popularityCount) {
        return CategoryResponseDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryType(categoryType)
                .popularityCount(popularityCount)
                .build();
    }
}
