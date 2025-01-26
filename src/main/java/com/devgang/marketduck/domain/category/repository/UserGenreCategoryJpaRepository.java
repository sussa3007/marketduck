package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.UserGenreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGenreCategoryJpaRepository extends JpaRepository<UserGenreCategory, Long> {
    void deleteByUser_UserId(Long userId);
}
