package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.GenreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreCategoryJpaRepository extends JpaRepository<GenreCategory, Long> {

    List<GenreCategory> findAllByGenreCategoryIdIn(List<Long> genreCategoryIds);
}
