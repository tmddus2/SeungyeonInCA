package com.hello.basic.dto;

import com.hello.basic.web.jwt.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDto {
    private boolean loginSuccess;
    private String email;
    private JwtToken googleAccessToken;
}
