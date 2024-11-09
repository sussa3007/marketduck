package com.devgang.marketduck.domain.feed.repository;

import com.devgang.marketduck.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {
    // 추가적인 JPA 쿼리 메소드가 필요하다면 여기에 선언합니다.
}
