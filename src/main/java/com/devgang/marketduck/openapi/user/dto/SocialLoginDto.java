package com.devgang.marketduck.openapi.user.dto;

import com.devgang.marketduck.constant.LoginType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SocialLoginDto {

    String oauthAccessToken;

    LoginType loginType;

}