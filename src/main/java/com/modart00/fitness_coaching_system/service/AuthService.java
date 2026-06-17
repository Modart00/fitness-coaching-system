package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.LoginRequest;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.entity.enums.Role;
import com.modart00.fitness_coaching_system.repository.UserRepository;
import com.modart00.fitness_coaching_system.security.JwtService;
import lombok.RequiredArgsConstructor;
import com.modart00.fitness_coaching_system.dto.AuthResponse;
import com.modart00.fitness_coaching_system.dto.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public AuthResponse register(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email veya şifre hatalı"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email veya şifre hatalı");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }


}

