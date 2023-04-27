package com.hello.basic.dto;

import com.hello.basic.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class SignInResponseDto {
    private boolean isSuccess;
    private User user;

    @Builder
    public SignInResponseDto(boolean isSuccess, User user) {
        this.isSuccess = isSuccess;
        this.user = user;
    }
}
