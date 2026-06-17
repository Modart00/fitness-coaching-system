package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.dto.request.UserUpdateRequest;
import com.modart00.fitness_coaching_system.dto.response.UserResponse;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/me/update")
    public UserResponse updateMe(@Valid @RequestBody UserUpdateRequest request, Authentication authentication){
        return userService.updateMe(request,authentication);
    }

    @GetMapping("/me")
    public UserResponse getMe(Authentication authentication){
        return userService.getMe(authentication);
    }


}
