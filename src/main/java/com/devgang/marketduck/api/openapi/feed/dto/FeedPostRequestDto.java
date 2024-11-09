package com.devgang.marketduck.api.openapi.feed.dto;

import com.devgang.marketduck.api.category.dto.CategoryDto;
import com.devgang.marketduck.constant.FeedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedPostRequestDto {

    private String title;

    private String content;

    private BigDecimal price;

    private FeedStatus feedStatus;

    private List<CategoryDto> feedGoodsCategories;

    private List<CategoryDto> feedGenreCategories;

}
