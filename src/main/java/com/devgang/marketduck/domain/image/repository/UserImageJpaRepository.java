package com.devgang.marketduck.domain.image.repository;

import com.devgang.marketduck.domain.image.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImageJpaRepository extends JpaRepository<UserImage, Long> {
    List<UserImage> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
