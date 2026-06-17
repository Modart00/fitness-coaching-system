package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    public void makeAdmin(User user){
        user.setRole(Role.ROLE_ADMIN);
    }

    public void makeCoach(User user){
        user.setRole(Role.ROLE_COACH);
    }

}
