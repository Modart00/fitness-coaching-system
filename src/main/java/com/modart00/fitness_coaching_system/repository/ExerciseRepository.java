package com.modart00.fitness_coaching_system.repository;

import com.modart00.fitness_coaching_system.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
