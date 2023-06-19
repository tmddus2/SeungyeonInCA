package com.hello.basic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.basic.dto.GoogleAccountDto;
import com.hello.basic.dto.SignInResponseDto;
import com.hello.basic.dto.SignUpDto;
import com.hello.basic.entity.User;
import com.hello.basic.oauth.GoogleOAuthToken;
import com.hello.basic.oauth.GoogleOauth;
import com.hello.basic.repository.UserRepository;
import com.hello.basic.web.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService {
    private final GoogleOauth googleOauth;
    private final UserRepository userRepository;
    private final UserService userService;

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

    public GoogleAccountDto requestUserInfo(GoogleOAuthToken token) {
        ResponseEntity<String> userInfo = googleOauth.getUserInfo(token);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(userInfo.getBody(), GoogleAccountDto.class);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        }
        return null;
    }


    public SignInResponseDto googleLogin(GoogleOAuthToken token) {
        GoogleAccountDto googleAccountInfo = requestUserInfo(token);
        if(userRepository.existsByEmail(googleAccountInfo.getEmail())) {
            Optional<User> user = userRepository.findByEmail(googleAccountInfo.getEmail());
            if (user.isPresent()) {
                return new SignInResponseDto(true, user.get().getEmail(), userService.login(user.get().getEmail(), null));
            }
        }

        userService.register(new SignUpDto(googleAccountInfo.getEmail(), "", ""));
        return new SignInResponseDto(true, googleAccountInfo.getEmail(), userService.login(googleAccountInfo.getEmail(), null));
    }
}
