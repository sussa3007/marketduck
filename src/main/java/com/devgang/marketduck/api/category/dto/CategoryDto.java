package com.devgang.marketduck.api.category.dto;

import com.devgang.marketduck.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long categoryId;

    private CategoryType categoryType;

}
