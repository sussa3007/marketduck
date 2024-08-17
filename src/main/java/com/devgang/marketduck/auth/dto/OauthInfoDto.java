package com.devgang.marketduck.auth.dto;

import com.devgang.marketduck.constant.Authority;
import com.devgang.marketduck.constant.LoginType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthInfoDto {
    private final String nickname;
    private final String email;
    private final LoginType loginType;
    private final Authority authority;
}
