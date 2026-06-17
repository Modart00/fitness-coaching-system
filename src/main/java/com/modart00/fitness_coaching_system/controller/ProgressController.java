package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.dto.request.ProgressRequest;
import com.modart00.fitness_coaching_system.dto.response.ProgressResponse;
import com.modart00.fitness_coaching_system.service.ProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PreAuthorize("hasAnyRole('COACH', 'ADMIN')")
    @PostMapping("/users/{userId}")
    public ProgressResponse saveForUser(@PathVariable Long userId,
                                        @Valid @RequestBody ProgressRequest request) {
        return progressService.saveForUser(userId, request);
    }

    @GetMapping("/me")
    public List<ProgressResponse> getMyProgress(Authentication authentication) {
        return progressService.getMyProgress(authentication);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ProgressResponse> getAll() {
        return progressService.getAll();
    }

    @PreAuthorize("hasAnyRole('COACH', 'ADMIN')")
    @PutMapping("/{id}")
    public ProgressResponse update(@PathVariable Long id,
                                   @Valid @RequestBody ProgressRequest request) {
        return progressService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('COACH', 'ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        progressService.deleteById(id);
    }
}
