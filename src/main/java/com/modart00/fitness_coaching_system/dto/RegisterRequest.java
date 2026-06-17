package com.modart00.fitness_coaching_system.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private String userName, email, password;
}
