package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.response.UserResponse;
import com.modart00.fitness_coaching_system.dto.request.UserUpdateRequest;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Page<UserResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::toResponse);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toResponse(user);
    }

    public UserResponse updateMe(UserUpdateRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!Objects.equals(user.getEmail(), request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (!Objects.equals(user.getUserName(), request.getUserName())
                && userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

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
