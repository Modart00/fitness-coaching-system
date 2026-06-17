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

    public ProgressResponse saveForUser(Long userId, ProgressRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

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

    public List<ProgressResponse> getAll() {
        return progressRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProgressResponse update(Long id, ProgressRequest request) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setCurrentWeight(request.getCurrentWeight());
        progress.setNote(request.getNote());

        return toResponse(progressRepository.save(progress));
    }

    public void deleteById(Long id) {
        if (!progressRepository.existsById(id)) {
            throw new RuntimeException("Progress not found");
        }

        progressRepository.deleteById(id);
    }
}