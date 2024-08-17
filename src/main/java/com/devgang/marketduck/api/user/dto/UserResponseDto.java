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
public class UserResponseDto {

    private Long userId;
    private String nickname;
    private String username;
    private String email;
    private String phoneNumber;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private UserStatus userStatus;
    private Authority authority;
    private LoginType loginType;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .userStatus(user.getUserStatus())
                .authority(user.getAuthority())
                .loginType(user.getLoginType())
                .build();
    }
}
