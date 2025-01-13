package com.devgang.marketduck.api.openapi.feed.dto;

import com.devgang.marketduck.constant.FeedStatus;
import com.devgang.marketduck.constant.FeedType;
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

    private String keyword;

    private FeedStatus status;

    private FeedType feedType;


}
