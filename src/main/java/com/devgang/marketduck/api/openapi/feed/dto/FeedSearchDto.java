package com.devgang.marketduck.api.openapi.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedSearchDto {

    private int page;

    private List<Long> genreIds;

    private List<Long> goodsIds;
}
