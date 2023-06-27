package com.hello.basic.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    Long id;
    String email;
}
