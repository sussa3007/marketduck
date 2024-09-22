package com.devgang.marketduck.utils;

import com.devgang.marketduck.constant.BaseUrl;
import org.springframework.stereotype.Component;

@Component
public class UrlCreateUtil {


    public static String createGoogleLoginRequestUrl(String token) {
        StringBuffer sb = new StringBuffer();
        sb.append(BaseUrl.GOOGLE_LOGIN.getUrl());
        sb.append(token);
        return sb.toString();
    }

    public static String createKakaoLoginRequestUrl() {
        return BaseUrl.KAKAO_LOGIN.getUrl();
    }

    public static String createNaverLoginRequestUrl() {
        return BaseUrl.NAVER_LOGIN.getUrl();
    }

}
