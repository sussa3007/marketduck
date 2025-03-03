package com.devgang.marketduck.domain.user.dto;

import com.devgang.marketduck.domain.category.dto.GoodsCategoryResponseDto;
import com.devgang.marketduck.domain.category.dto.GenreCategoryResponseDto;
import com.devgang.marketduck.domain.category.entity.UserGoodsCategory;
import com.devgang.marketduck.domain.category.entity.UserGenreCategory;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.constant.Authority;
import com.devgang.marketduck.constant.LoginType;
import com.devgang.marketduck.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDto {
    private Long userId;
    private String nickname;
    private String username;
    private String phoneNumber;
    private String profileImageUrl;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private UserStatus userStatus;
    private Authority authority;
    private LoginType loginType;
    private List<String> roles;
    private List<GoodsCategoryResponseDto> goodsCategories;
    private List<GenreCategoryResponseDto> genreCategories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDetailResponseDto from(User user) {
        List<GoodsCategoryResponseDto> goodsCategories = user.getUserGoodsCategories().stream()
                .map(UserGoodsCategory::getGoodsCategory)
                .map(GoodsCategoryResponseDto::from)
                .collect(Collectors.toList());

        List<GenreCategoryResponseDto> genreCategories = user.getUserGenreCategories().stream()
                .map(UserGenreCategory::getGenreCategory)
                .map(GenreCategoryResponseDto::from)
                .collect(Collectors.toList());

        return UserDetailResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .userStatus(user.getUserStatus())
                .authority(user.getAuthority())
                .loginType(user.getLoginType())
                .roles(user.getRoles())
                .goodsCategories(goodsCategories)
                .genreCategories(genreCategories)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}