package com.devgang.marketduck.api.openapi.feed.dto;

import com.devgang.marketduck.domain.image.entity.FeedImage;
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

    public static FeedImageResponseDto of(FeedImage image) {
        return FeedImageResponseDto.builder()
                .feedImageId(image.getFeedImageId())
                .feedId(image.getFeed().getFeedId())
                .fileUrl(image.getFileUrl())
                .imageIndex(image.getImageIndex())
                .createdAt(image.getCreatedAt())
                .updatedAt(image.getUpdatedAt())
                .build();
    }

}
