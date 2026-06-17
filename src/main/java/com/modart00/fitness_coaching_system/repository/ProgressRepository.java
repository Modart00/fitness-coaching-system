package com.modart00.fitness_coaching_system.repository;

import com.modart00.fitness_coaching_system.entity.Progress;
import com.modart00.fitness_coaching_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress,Long> {
    List<Progress> findByUser(User user);
}
