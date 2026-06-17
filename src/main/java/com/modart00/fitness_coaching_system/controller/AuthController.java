package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.dto.response.AuthResponse;
import com.modart00.fitness_coaching_system.dto.request.LoginRequest;
import com.modart00.fitness_coaching_system.dto.request.RegisterRequest;
import com.modart00.fitness_coaching_system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test(){
        return "endpoint çalıştı";
    }

}
