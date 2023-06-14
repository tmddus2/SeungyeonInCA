package com.hello.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class SignUpResponseDto {
    private Long id;
    private String email;
}
