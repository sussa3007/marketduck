package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.FeedGenreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedGenreCategoryJpaRepository extends JpaRepository<FeedGenreCategory, Long> {
}
