package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
@CrossOrigin
public class FileController {

    FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileService.upload(file);

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/")
                .path(fileName)
                .toUriString();
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws IOException {
        Resource resource = fileService.load(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileService.getContentType(fileName)))
                .body(resource);
    }
}
