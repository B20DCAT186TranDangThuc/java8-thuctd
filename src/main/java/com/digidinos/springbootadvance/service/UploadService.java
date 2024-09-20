package com.digidinos.springbootadvance.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadService {

    public byte[] upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/png") && !contentType.equals("image/jpeg"))) {
            throw new IllegalArgumentException("File type must be PNG or JPEG");
        }

        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}