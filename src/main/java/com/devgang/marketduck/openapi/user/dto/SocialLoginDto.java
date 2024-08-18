package com.devgang.marketduck.openapi.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SocialLoginDto {

    String oauthAccessToken;

    String loginType;

}