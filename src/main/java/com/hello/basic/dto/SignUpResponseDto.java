package com.hello.basic.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SignUpResponseDto {
    private Long id;
    private String email;

    @Builder
    public  SignUpResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
