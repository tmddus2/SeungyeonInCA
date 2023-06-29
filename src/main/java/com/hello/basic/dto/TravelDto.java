package com.hello.basic.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TravelDto {
    private String name;
    private String description;
    private MultipartFile file;
}
