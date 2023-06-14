package com.hello.basic.dto;

import lombok.Data;

@Data
public class SignInResponseDto {
    private boolean loginSuccess;
    private String email;
    private String googleAccessToken;
}
