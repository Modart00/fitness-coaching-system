package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.request.WorkoutPlanRequest;
import com.modart00.fitness_coaching_system.dto.response.WorkoutPlanResponse;
import com.modart00.fitness_coaching_system.entity.WorkoutPlan;
import com.modart00.fitness_coaching_system.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanResponse toResponse(WorkoutPlan workoutPlan){
        return new WorkoutPlanResponse(workoutPlan.getTitle(), workoutPlan.getDescription(), workoutPlan.getDurationWeek(), workoutPlan.getExercises());
    }

    public WorkoutPlanResponse save(WorkoutPlanRequest request){
       WorkoutPlan workoutPlan = new WorkoutPlan();

       workoutPlan.setTitle(request.getTitle());
       workoutPlan.setDescription(request.getDescription());
       workoutPlan.setDurationWeek(request.getDurationWeek());
       workoutPlan.setExercises(request.getExercises());

       WorkoutPlan savedPlan = workoutPlanRepository.save(workoutPlan);

       return toResponse(savedPlan);
    }

    public List<WorkoutPlanResponse> getAll(){

        return workoutPlanRepository.findAll().stream().map(this::toResponse).toList();

    }

    public WorkoutPlanResponse findById(Long id){
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        return toResponse(workoutPlan);
    }

    public WorkoutPlanResponse findByTitle(String title){
        WorkoutPlan workoutPlan = workoutPlanRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        return toResponse(workoutPlan);
    }

    public WorkoutPlanResponse update(Long id, WorkoutPlanRequest request){

        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        workoutPlan.setTitle(request.getTitle());
        workoutPlan.setDescription(request.getDescription());
        workoutPlan.setDurationWeek(request.getDurationWeek());
        workoutPlan.setExercises(request.getExercises());

        workoutPlanRepository.save(workoutPlan);

        return toResponse(workoutPlan);
    }

    public void deleteById(Long id){
        workoutPlanRepository.deleteById(id);
    }

}
