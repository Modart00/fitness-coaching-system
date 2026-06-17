package com.modart00.fitness_coaching_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double currentWeight;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
