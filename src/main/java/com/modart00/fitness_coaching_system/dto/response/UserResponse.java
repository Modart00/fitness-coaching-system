package com.modart00.fitness_coaching_system.dto.response;

import com.modart00.fitness_coaching_system.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String email;
    private Role role;
}