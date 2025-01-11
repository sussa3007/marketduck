package com.devgang.marketduck.api.openapi.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFeedSearchDto {

    private int page;
    private Long userId;
}
