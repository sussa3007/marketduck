package com.devgang.marketduck.api.openapi.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedImageResponseDto {

    private Long feedImageId;

    private Long feedId;

    private String fileUrl;

    private int imageIndex;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
