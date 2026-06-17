package com.modart00.fitness_coaching_system.Repository;

import com.modart00.fitness_coaching_system.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress,Long> {
}
