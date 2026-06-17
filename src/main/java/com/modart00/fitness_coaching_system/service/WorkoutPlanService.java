package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.request.WorkoutPlanRequest;
import com.modart00.fitness_coaching_system.dto.response.WorkoutPlanResponse;
import com.modart00.fitness_coaching_system.entity.Exercise;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.entity.WorkoutPlan;
import com.modart00.fitness_coaching_system.repository.ExerciseRepository;
import com.modart00.fitness_coaching_system.repository.UserRepository;
import com.modart00.fitness_coaching_system.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutPlanResponse toResponse(WorkoutPlan workoutPlan){
        return new WorkoutPlanResponse(workoutPlan.getTitle(), workoutPlan.getDescription(), workoutPlan.getDurationWeek(), workoutPlan.getExercises());
    }

    public WorkoutPlanResponse saveForUser(Long userId, WorkoutPlanRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkoutPlan workoutPlan = new WorkoutPlan();

        workoutPlan.setTitle(request.getTitle());
        workoutPlan.setDescription(request.getDescription());
        workoutPlan.setDurationWeek(request.getDurationWeek());
        List<Exercise> exercises = exerciseRepository.findAllById(request.getExerciseIds());
        workoutPlan.setExercises(exercises);
        workoutPlan.setUser(user);

        return toResponse(workoutPlanRepository.save(workoutPlan));
    }

    public List<WorkoutPlanResponse> getMyPlans(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return workoutPlanRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
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
        List<Exercise> exercises = exerciseRepository.findAllById(request.getExerciseIds());
        workoutPlan.setExercises(exercises);
        workoutPlanRepository.save(workoutPlan);

        return toResponse(workoutPlan);
    }

    public void deleteById(Long id){
        workoutPlanRepository.deleteById(id);
    }

}
