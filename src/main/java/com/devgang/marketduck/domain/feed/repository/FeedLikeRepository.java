package com.devgang.marketduck.domain.feed.repository;

import com.devgang.marketduck.domain.feed.entity.Feed;
import com.devgang.marketduck.domain.feed.entity.FeedLike;
import com.devgang.marketduck.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

    Optional<FeedLike> findByFeedAndUser(Feed feed, User user);

    boolean existsByFeedAndUser(Feed feed, User user);

    List<FeedLike> findByUser(User user);

    List<FeedLike> findByFeed(Feed feed);

    @Query("SELECT COUNT(fl) FROM FeedLike fl WHERE fl.feed = :feed")
    int countByFeed(@Param("feed") Feed feed);

    void deleteByFeedAndUser(Feed feed, User user);
}