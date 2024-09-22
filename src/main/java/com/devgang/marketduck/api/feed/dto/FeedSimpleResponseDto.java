package com.devgang.marketduck.api.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedSimpleResponseDto {

    private Long feedId;  // ID 필드를 feedId로 변경

    private String genreCategory;

    private String goodsCategory;

    private String title;

    private Double price;

    private String content;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
