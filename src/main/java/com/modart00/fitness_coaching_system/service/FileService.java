package com.modart00.fitness_coaching_system.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    String uploadDir = "uploads/";

    public String upload(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Yuklenen dosya bos olamaz");
        }

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalName;
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public Resource load(String fileName) {
        try {
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("Gecersiz dosya adi");
            }

            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new IllegalArgumentException("Dosya bulunamadi");
            }

            return resource;
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("Gecersiz dosya adi", exception);
        }
    }

    public String getContentType(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        String contentType = Files.probeContentType(filePath);
        return contentType == null ? "application/octet-stream" : contentType;
    }
}
