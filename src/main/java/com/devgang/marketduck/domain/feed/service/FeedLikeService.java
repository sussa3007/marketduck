package com.devgang.marketduck.domain.feed.service;

import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.domain.feed.dto.FeedLikeResponseDto;
import com.devgang.marketduck.domain.feed.entity.Feed;
import com.devgang.marketduck.domain.feed.entity.FeedLike;
import com.devgang.marketduck.domain.feed.repository.FeedLikeRepository;
import com.devgang.marketduck.domain.feed.repository.FeedRepository;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.domain.user.repository.UserRepository;
import com.devgang.marketduck.exception.ServiceLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    @Transactional  
    public FeedLikeResponseDto toggleLike(Long feedId, Long userId) {
        Feed feed = feedRepository.findFeedById(feedId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER));

        Optional<FeedLike> existingLike = feedLikeRepository.findByFeedAndUser(feed, user);

        if (existingLike.isPresent()) {
            // 이미 찜한 경우 -> 찜 취소
            FeedLike feedLike = existingLike.get();
            feed.removeFeedLike(feedLike);
            user.removeLikedFeed(feedLike);
            feedLikeRepository.delete(feedLike);
            return null; // 찜 취소됨
        } else {
            // 찜하지 않은 경우 -> 찜하기
            FeedLike feedLike = FeedLike.create(feed, user);
            feed.addFeedLike(feedLike);
            user.addLikedFeed(feedLike);
            FeedLike saveFeedLike = feedLikeRepository.save(feedLike);
            return FeedLikeResponseDto.from(saveFeedLike); // 찜 추가됨
        }
    }

    @Transactional(readOnly = true)
    public boolean isLikedByUser(Long feedId, Long userId) {
        Feed feed = feedRepository.findFeedById(feedId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER));

        return feedLikeRepository.existsByFeedAndUser(feed, user);
    }

    @Transactional(readOnly = true)
    public int getLikeCount(Long feedId) {
        Feed feed = feedRepository.findFeedById(feedId);

        return feedLikeRepository.countByFeed(feed);
    }

    @Transactional(readOnly = true)
    public List<FeedLikeResponseDto> getLikedFeedsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER));

        return feedLikeRepository.findByUser(user).stream()
                .map(FeedLikeResponseDto::from).toList();
    }
}