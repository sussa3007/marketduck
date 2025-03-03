package com.devgang.marketduck.api.feed.controller;


import com.devgang.marketduck.api.feed.dto.FeedImagePatchDto;
import com.devgang.marketduck.api.openapi.feed.dto.*;
import com.devgang.marketduck.domain.feed.dto.FeedLikeResponseDto;
import com.devgang.marketduck.domain.feed.service.FeedService;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.dto.Result;
import com.devgang.marketduck.domain.feed.service.FeedLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController implements FeedControllerIfs{

    private final FeedService feedService;

    private final FeedLikeService feedLikeService;

    @Override
    @GetMapping
    public ResponseEntity<PageResponseDto<List<FeedSimpleResponseDto>>> getFeedList(User user, FeedSearchDto requestDto) {
        Page<FeedSimpleResponseDto> response = feedService.findAllFeedForAdmin(requestDto, user);
        return ResponseEntity.ok(PageResponseDto.of(response, response.getContent(), Result.ok()));
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> postFeed(User user, FeedPostRequestDto requestDto) {
        FeedDetailResponseDto response = feedService.createFeed(requestDto, user);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @PostMapping("/image/{feedId}")
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> postFeedImage(Long feedId, MultipartFile[] multipartFile, User user) {
        FeedDetailResponseDto response = feedService.createFeedImage(user, feedId, multipartFile);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @PatchMapping("/image")
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> patchFeedImage(FeedImagePatchDto request, User user) {
        FeedDetailResponseDto response = feedService.updateFeedImage(request.getFeedId(), request.getIndexIdList(), user);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }


    @Override
    @PatchMapping("/{feedId}")
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> patchFeed(Long feedId, User user, FeedPatchRequestDto requestDto) {
        FeedDetailResponseDto response = feedService.updateFeed(feedId, requestDto, user);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }


    @Override
    @DeleteMapping("/{feedId}")
    public ResponseEntity<ResponseDto> deleteFeed(User user, Long feedId) {
        feedService.deleteFeed(feedId, user);
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }

    @Override
    @PostMapping("/{feedId}/like")
    public ResponseEntity<ResponseDto<FeedLikeResponseDto>> postFeedLike(Long feedId, User user) {
        FeedLikeResponseDto response = feedLikeService.toggleLike(feedId, user.getUserId());
        if (response == null) {
            return ResponseEntity.ok(ResponseDto.of(Result.ok()));
        } else {
            return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
        }
    }

    @Override
    @GetMapping("/likes")
    public ResponseEntity<PageResponseDto<List<FeedLikeResponseDto>>> getFeedLike(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedLike'");
    }
}
