package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.request.ExerciseRequest;
import com.modart00.fitness_coaching_system.dto.response.ExerciseResponse;
import com.modart00.fitness_coaching_system.entity.Exercise;
import com.modart00.fitness_coaching_system.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private ExerciseResponse toResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getSetCount(),
                exercise.getRepCount()
        );
    }

    public ExerciseResponse save(ExerciseRequest request) {
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setSetCount(request.getSetCount());
        exercise.setRepCount(request.getRepCount());

        return toResponse(exerciseRepository.save(exercise));
    }

    public List<ExerciseResponse> getAll() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ExerciseResponse findById(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        return toResponse(exercise);
    }

    public ExerciseResponse update(Long id, ExerciseRequest request) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        exercise.setName(request.getName());
        exercise.setSetCount(request.getSetCount());
        exercise.setRepCount(request.getRepCount());

        return toResponse(exerciseRepository.save(exercise));
    }

    public void deleteById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new RuntimeException("Exercise not found");
        }

        exerciseRepository.deleteById(id);
    }
}