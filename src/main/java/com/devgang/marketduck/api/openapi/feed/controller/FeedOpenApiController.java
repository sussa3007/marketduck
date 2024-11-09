package com.devgang.marketduck.api.openapi.feed.controller;

import com.devgang.marketduck.api.openapi.feed.dto.FeedDetailResponseDto;
import com.devgang.marketduck.api.openapi.feed.dto.FeedSearchDto;
import com.devgang.marketduck.api.openapi.feed.dto.FeedSimpleResponseDto;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open-api/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedOpenApiController implements FeedOpenApiControllerIfs {

    @Override
    @GetMapping
    public ResponseEntity<PageResponseDto<List<FeedSimpleResponseDto>>> getFeedList(FeedSearchDto requestDto) {
        return null;
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<PageResponseDto<List<FeedSimpleResponseDto>>> getFeedListByUser(Long userId) {
        return null;
    }

    @Override
    @GetMapping("/detail/{feedId}")
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> getFeed(Long feedId) {
        return null;
    }
}
