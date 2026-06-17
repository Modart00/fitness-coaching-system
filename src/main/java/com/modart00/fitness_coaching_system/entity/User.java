package com.modart00.fitness_coaching_system.entity;

import com.modart00.fitness_coaching_system.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String password;
    private int age;
    private double weight;
    private double height;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Coach coach;

    @OneToMany(mappedBy = "user")
    private List<WorkoutPlan> workoutPlans;

    @OneToMany(mappedBy = "user")
    private List<Progress> progresses;
}
