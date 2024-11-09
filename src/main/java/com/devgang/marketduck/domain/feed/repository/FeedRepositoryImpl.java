package com.devgang.marketduck.domain.feed.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepository{

    private final JPAQueryFactory queryFactory;

    private final FeedJpaRepository feedJpaRepository;

}
