package com.modart00.fitness_coaching_system.entity;

import com.modart00.fitness_coaching_system.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String refreshToken;
    private LocalDateTime refreshTokenExpiryDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Coach coach;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<WorkoutPlan> workoutPlans;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Progress> progresses;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private VerificationToken token;

    private boolean enabled;
}
