package com.hello.basic.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String picture;
}
