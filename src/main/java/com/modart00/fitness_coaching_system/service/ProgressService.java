package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.request.ProgressRequest;
import com.modart00.fitness_coaching_system.dto.response.ProgressResponse;
import com.modart00.fitness_coaching_system.entity.Progress;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.repository.ProgressRepository;
import com.modart00.fitness_coaching_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;

    private ProgressResponse toResponse(Progress progress) {
        return new ProgressResponse(
                progress.getId(),
                progress.getCurrentWeight(),
                progress.getNote(),
                progress.getCreatedAt()
        );
    }

    public ProgressResponse save(ProgressRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Progress progress = new Progress();
        progress.setCurrentWeight(request.getCurrentWeight());
        progress.setNote(request.getNote());
        progress.setCreatedAt(LocalDate.now());
        progress.setUser(user);

        return toResponse(progressRepository.save(progress));
    }

    public List<ProgressResponse> getMyProgress(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return progressRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProgressResponse findById(Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        if (!progress.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to access this progress");
        }

        return toResponse(progress);
    }

    public ProgressResponse update(Long id, ProgressRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        if (!progress.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to update this progress");
        }

        progress.setCurrentWeight(request.getCurrentWeight());
        progress.setNote(request.getNote());

        return toResponse(progressRepository.save(progress));
    }

    public void deleteById(Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        if (!progress.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this progress");
        }

        progressRepository.delete(progress);
    }
}