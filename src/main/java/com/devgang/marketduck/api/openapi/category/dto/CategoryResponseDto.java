package com.devgang.marketduck.api.openapi.category.dto;

import com.devgang.marketduck.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long categoryId;

    private String categoryName;

    private CategoryType categoryType;

}
