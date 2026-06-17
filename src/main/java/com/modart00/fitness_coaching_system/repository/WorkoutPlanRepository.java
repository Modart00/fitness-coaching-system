package com.modart00.fitness_coaching_system.repository;

import com.modart00.fitness_coaching_system.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan,Long> {
    Optional<WorkoutPlan> findByTitle();
}
