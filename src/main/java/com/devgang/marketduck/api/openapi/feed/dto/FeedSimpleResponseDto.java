package com.devgang.marketduck.api.openapi.feed.dto;

import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.constant.FeedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedSimpleResponseDto {

    private Long feedId;  // ID 필드를 feedId로 변경

    private List<CategoryResponseDto> genreCategory;

    private List<CategoryResponseDto> goodsCategory;
    private String title;

    private BigDecimal price;

    private String content;

    private int viewCount;

    private int likeCount;

    private FeedStatus status;

    private String mainImageUrl;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
