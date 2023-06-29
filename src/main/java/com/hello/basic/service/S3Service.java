package com.hello.basic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Uploader s3Uploader;

    public String saveFile(MultipartFile file) throws IOException {
        return s3Uploader.upload(file, "images");
    }

}
