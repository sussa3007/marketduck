package com.devgang.marketduck.api.openapi.feed.dto;

import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.user.dto.UserSimpleResponseDto;
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
public class FeedDetailResponseDto {

    private Long feedId;  // ID 필드를 feedId로 변경

    private UserSimpleResponseDto userInfo;

    private List<CategoryResponseDto> genreCategory;

    private List<CategoryResponseDto> goodsCategory;

    private String title;

    private BigDecimal price;

    private String content;

    private int likeCount;
    private int viewCount;

    private FeedStatus status;

    private List<FeedImageResponseDto> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
