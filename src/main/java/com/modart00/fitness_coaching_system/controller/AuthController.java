package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.dto.request.RefreshTokenRequest;
import com.modart00.fitness_coaching_system.dto.response.LoginResponse;
import com.modart00.fitness_coaching_system.dto.request.LoginRequest;
import com.modart00.fitness_coaching_system.dto.request.RegisterRequest;
import com.modart00.fitness_coaching_system.dto.response.RegisterResponse;
import com.modart00.fitness_coaching_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request){

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refresh(@RequestBody RefreshTokenRequest request){
    return authService.refreshToken(request.getRefreshToken());
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String token) {
        authService.verifyAccount(token);
        return "Account verified";
    }

}
