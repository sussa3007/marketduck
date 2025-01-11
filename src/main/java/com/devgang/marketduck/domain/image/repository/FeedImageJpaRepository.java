package com.devgang.marketduck.domain.image.repository;

import com.devgang.marketduck.domain.image.entity.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedImageJpaRepository extends JpaRepository<FeedImage, Long> {

    List<FeedImage> findAllByFeed_FeedIdOrderByImageIndexAsc(Long feedId);

    void deleteAllByFeed_FeedId(Long feedId);

    void deleteAllByFeed_FeedIdAndImageIndexIn(Long feedId, List<Integer> imageIndexes);
}
