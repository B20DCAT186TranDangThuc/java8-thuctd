package com.digidinos.shopping.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    // Đường dẫn tới thư mục "uploads" nằm ở gốc của dự án
    private final String UPLOAD_DIR = "uploads/";

    public String upload(MultipartFile file) {
        try {
            // Tạo thư mục "uploads" nếu nó chưa tồn tại
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Lấy tên file gốc và phần mở rộng
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // Tạo tên file mới với định dạng name-time.png
            String newFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."))
                    + "-" + System.currentTimeMillis()
                    + extension;

            // Đường dẫn lưu file
            Path path = Paths.get(UPLOAD_DIR + newFilename);

            // Copy file tới thư mục "uploads"
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return newFilename;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
