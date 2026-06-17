package com.modart00.fitness_coaching_system.entity;

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

    @ManyToOne
    private Coach coach;

    @OneToMany
    private List<WorkoutPlan> workoutPlans;

    @OneToMany
    private List<Progress> progresses;
}
