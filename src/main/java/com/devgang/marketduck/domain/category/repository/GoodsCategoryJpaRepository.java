package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsCategoryJpaRepository extends JpaRepository<GoodsCategory, Long> {

    List<GoodsCategory> findAllByGoodsCategoryIdIn(List<Long> goodsCategoryIds);
}
