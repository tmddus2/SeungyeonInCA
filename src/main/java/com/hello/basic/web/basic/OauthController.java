package com.hello.basic.web.basic;

import com.hello.basic.oauth.GoogleOAuthToken;
import com.hello.basic.oauth.GoogleOauth;
import com.hello.basic.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class OauthController {
    private final OauthService oauthService;
    private final GoogleOauth googleOauth;

    @GetMapping("/login/ouath2/google")
    public void googleLogin(HttpServletResponse response) throws Exception {
        oauthService.request(response);
        log.info("google login");
    }

    @GetMapping("/login/ouath2/google/callback")
    public ResponseEntity<String> callback(@RequestParam(name = "code") String code) {
        GoogleOAuthToken token = oauthService.requestAccessToken(code);
        return oauthService.requestUserInfo(token);
    }

}
