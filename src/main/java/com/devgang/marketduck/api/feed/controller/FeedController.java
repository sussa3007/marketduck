package com.devgang.marketduck.api.feed.controller;


import com.devgang.marketduck.api.openapi.feed.dto.FeedDetailResponseDto;
import com.devgang.marketduck.api.openapi.feed.dto.FeedImageResponseDto;
import com.devgang.marketduck.api.openapi.feed.dto.FeedPatchRequestDto;
import com.devgang.marketduck.api.openapi.feed.dto.FeedPostRequestDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController implements FeedControllerIfs{
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> postFeed(User user, FeedPostRequestDto requestDto) {
        return null;
    }

    @Override
    @PostMapping("/image/{feedId}")
    public ResponseEntity<ResponseDto<FeedImageResponseDto>> postFeedImage(Long feedId, MultipartFile[] multipartFile, User user) {
        // todo 이미지 등록시 이미 등록된 이미지들이 있다면 인덱스 정렬 한다음 제일 마지막 인덱스 값으로 이미지 저장
        return null;
    }

    @Override
    @PatchMapping("/{feedId}/{index}")
    public ResponseEntity<ResponseDto<FeedImageResponseDto>> patchFeedImage(Long feedId, Long index, User user) {
        return null;
    }

    @Override
    @PatchMapping("/{feedId}")
    public ResponseEntity<ResponseDto<FeedDetailResponseDto>> patchFeed(Long feedId, User user, FeedPatchRequestDto requestDto) {
        return null;
    }


    @Override
    @DeleteMapping("/{feedId}")
    public ResponseEntity<ResponseDto> deleteFeed(User user, Long feedId) {
        return null;
    }
}
