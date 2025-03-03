package com.devgang.marketduck.domain.feed.dto;

import com.devgang.marketduck.api.openapi.feed.dto.FeedSimpleResponseDto;
import com.devgang.marketduck.domain.feed.entity.FeedLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedLikeResponseDto {
    private Long feedLikeId;
    private Long feedId;
    private String feedTitle;
    private Long userId;
    private String userNickname;
    private FeedSimpleResponseDto feedSimpleResponseDto;
    private LocalDateTime createdAt;

    public static FeedLikeResponseDto from(FeedLike feedLike) {
        return FeedLikeResponseDto.builder()
                .feedLikeId(feedLike.getFeedLikeId())
                .feedId(feedLike.getFeed().getFeedId())
                .feedTitle(feedLike.getFeed().getTitle())
                .userId(feedLike.getUser().getUserId())
                .userNickname(feedLike.getUser().getNickname())
                .feedSimpleResponseDto(FeedSimpleResponseDto.of(feedLike.getFeed()))
                .createdAt(feedLike.getCreatedAt())
                .build();
    }
}