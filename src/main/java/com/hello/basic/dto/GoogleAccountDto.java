package com.hello.basic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleAccountDto {
    private String id;
    private String email;
    private String name;
    private String picture;
}
