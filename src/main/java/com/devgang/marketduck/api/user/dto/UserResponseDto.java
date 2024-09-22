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
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String nickname;
    private String username;
    private String profileImageUrl;
    private String phoneNumber;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private UserStatus userStatus;
    private Authority authority;
    private LoginType loginType;

    private List<String> roles;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
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
                .build();
    }
}
