package com.hello.basic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.basic.oauth.GoogleOAuthToken;
import com.hello.basic.oauth.GoogleOauth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService {
    private final GoogleOauth googleOauth;
    private final ObjectMapper objectMapper;

    public void request(HttpServletResponse response) throws IOException {
        String redirectURL = googleOauth.getOauthRedirectURL();
        response.sendRedirect(redirectURL);
    }

    public GoogleOAuthToken requestAccessToken(String code) {
        try {
            return googleOauth.requestAccessToken(code);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> requestUserInfo(GoogleOAuthToken token) {
        ResponseEntity<String> userInfo = googleOauth.getUserInfo(token);
        return userInfo;
    }
}
