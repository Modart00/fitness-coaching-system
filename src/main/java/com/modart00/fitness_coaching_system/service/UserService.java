package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.response.UserResponse;
import com.modart00.fitness_coaching_system.dto.request.UserUpdateRequest;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserResponse getMe(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return toResponse(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toResponse(user);
    }

    public UserResponse updateMe(UserUpdateRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());

        return toResponse(userRepository.save(user));
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}