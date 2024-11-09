package com.devgang.marketduck.api.user.dto;


import com.devgang.marketduck.constant.Authority;
import com.devgang.marketduck.constant.LoginType;
import com.devgang.marketduck.constant.UserStatus;
import com.devgang.marketduck.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleResponseDto {

    private Long userId;
    private String nickname;
    private String username;
    private String profileImageUrl;
    private UserStatus userStatus;
    private Authority authority;
    private LoginType loginType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserSimpleResponseDto of(User user) {
        return UserSimpleResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .profileImageUrl(user.getProfileImageUrl())
                .userStatus(user.getUserStatus())
                .authority(user.getAuthority())
                .loginType(user.getLoginType())
                .build();
    }
}
