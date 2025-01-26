package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.UserGoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGoodsCategoryJpaRepository extends JpaRepository<UserGoodsCategory, Long> {
    void deleteByUser_UserId(Long userId);
}
