package com.hello.basic.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class TravelDto {
    private Long id;
    private String name;
    private String description;
    private MultipartFile file;

    @Builder
    public TravelDto(Long id) {
        this.id = id;
    }
}
