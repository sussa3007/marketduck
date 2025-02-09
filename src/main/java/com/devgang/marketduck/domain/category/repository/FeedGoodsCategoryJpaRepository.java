package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.FeedGoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedGoodsCategoryJpaRepository extends JpaRepository<FeedGoodsCategory, Long> {

    void deleteAllByFeed_FeedId(Long feedId);

}
