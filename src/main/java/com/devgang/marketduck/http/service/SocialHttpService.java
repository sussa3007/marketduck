package com.devgang.marketduck.http.service;

import com.devgang.marketduck.api.openapi.user.dto.SocialLoginDto;
import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.constant.LoginType;
import com.devgang.marketduck.exception.ServiceLogicException;
import com.devgang.marketduck.utils.UrlCreateUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialHttpService {



    public String generateLoginRequest(SocialLoginDto dto) {
        LoginType loginType = dto.getLoginType();
        String requestUrl;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            if (loginType.equals(LoginType.KAKAO)) {
                log.info("Kakao Login Request");
                log.info("Kakao Login Token = {}", dto.getOauthAccessToken());
                requestUrl = UrlCreateUtil.createKakaoLoginRequestUrl();
                HttpGet httpGet = new HttpGet(requestUrl);
                httpGet.setHeader(org.apache.http.HttpHeaders.CONTENT_TYPE, "application/json");
                httpGet.setHeader(org.apache.http.HttpHeaders.AUTHORIZATION,"Bearer "+dto.getOauthAccessToken());
                return (String) httpclient.execute(httpGet, getLoginHandler(loginType));
            } else if (loginType.equals(LoginType.NAVER)) {
                log.info("Naver Login Request");
                log.info("Naver Login Token = {}", dto.getOauthAccessToken());
                requestUrl = UrlCreateUtil.createNaverLoginRequestUrl();
                HttpGet httpGet = new HttpGet(requestUrl);
                httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                httpGet.setHeader("Authorization","Bearer "+dto.getOauthAccessToken());
                return (String) httpclient.execute(httpGet, getLoginHandler(loginType));
            } else if (loginType.equals(LoginType.GOOGLE)) {
                log.info("Google Login Request");
                log.info("Google Login Token = {}", dto.getOauthAccessToken());
                requestUrl = UrlCreateUtil.createGoogleLoginRequestUrl(dto.getOauthAccessToken());
                HttpGet httpGet = new HttpGet(requestUrl);
                return (String) httpclient.execute(httpGet, getLoginHandler(loginType));
            } else {
                throw new ServiceLogicException(ErrorCode.BAD_REQUEST, "Not Found Provider Login Type");
            }
        } catch (IOException e) {
            throw new ServiceLogicException(ErrorCode.HTTP_REQUEST_IO_ERROR);
        }
    }
    private ResponseHandler<?> getLoginHandler(LoginType loginType) {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity responseBody = response.getEntity();
                String res = EntityUtils.toString(responseBody);
                if (loginType.equals(LoginType.KAKAO)) {
                    log.info("Kakao Email Account = {}", res);
                    JsonElement kakaoElement = JsonParser.parseString(res);
                    JsonElement kakaoAccount = kakaoElement.getAsJsonObject().get("kakao_account");
                    String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
                    return email;
                } else if (loginType.equals(LoginType.NAVER)) {
                    JsonElement naverElement = JsonParser.parseString(res);
                    JsonElement naverAccount = naverElement.getAsJsonObject().get("response");
                    String email = naverAccount.getAsJsonObject().get("email").getAsString();
                    return email;
                } else if (loginType.equals(LoginType.GOOGLE)) {
                    JsonElement googleElement = JsonParser.parseString(res);
                    String email = googleElement.getAsJsonObject().get("email").getAsString();
                    log.info("Google Email Account = {}", email);
                    return email;
                } else {
                    throw new ServiceLogicException(ErrorCode.BAD_REQUEST, "Not Found Provider Login Type");
                }

            } else {
                //Todo : Status Code 활용하여 예외처리
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }

}









