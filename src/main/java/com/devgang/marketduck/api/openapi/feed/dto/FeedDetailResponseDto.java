package com.devgang.marketduck.api.openapi.feed.dto;

import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.user.dto.UserSimpleResponseDto;
import com.devgang.marketduck.constant.CategoryType;
import com.devgang.marketduck.constant.FeedStatus;
import com.devgang.marketduck.constant.FeedType;
import com.devgang.marketduck.domain.feed.entity.Feed;
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

    private FeedType feedType;

    private List<FeedImageResponseDto> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static FeedDetailResponseDto of(Feed feed) {
        return FeedDetailResponseDto.builder()
                .feedId(feed.getFeedId())
                .userInfo(UserSimpleResponseDto.of(feed.getUser()))
                .genreCategory(feed.getFeedGenreCategories().stream()
                        .map(category -> new CategoryResponseDto(category.getGenreCategory().getGenreCategoryId(),
                                category.getGenreCategory().getGenreCategoryName(),
                                CategoryType.GENRE))
                        .toList())
                .goodsCategory(feed.getFeedGoodsCategories().stream()
                        .map(category -> new CategoryResponseDto(category.getGoodsCategory().getGoodsCategoryId(),
                                category.getGoodsCategory().getGoodsCategoryName(),
                                CategoryType.GOODS))
                        .toList())
                .title(feed.getTitle())
                .price(feed.getPrice())
                .content(feed.getContent())
                .likeCount(feed.getLikeCount())
                .viewCount(feed.getViewCount())
                .status(feed.getFeedStatus())
                .feedType(feed.getFeedType())
                .images(feed.getFeedImages().stream()
                        .map(FeedImageResponseDto::of)
                        .toList())
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }
    
}
