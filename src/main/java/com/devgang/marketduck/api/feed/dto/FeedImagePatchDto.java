package com.devgang.marketduck.api.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedImagePatchDto {

    private Long feedId;

    private List<Integer> indexIdList;

}
