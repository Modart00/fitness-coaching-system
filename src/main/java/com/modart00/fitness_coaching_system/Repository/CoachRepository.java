package com.modart00.fitness_coaching_system.Repository;

import com.modart00.fitness_coaching_system.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach,Long> {
    Optional<Coach> findBySpecialty(String specialty);
}
