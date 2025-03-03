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
public class CategorySearchDto {

    private int page;

    private int size;

    private String categoryName;

    private CategoryType categoryType;

    // 인기순 여부
    private boolean isPopular;

}
