package com.modart00.fitness_coaching_system.repository;

import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan,Long> {
    Optional<WorkoutPlan> findByTitle(String title);
    List<WorkoutPlan> findByUser(User user);
}
