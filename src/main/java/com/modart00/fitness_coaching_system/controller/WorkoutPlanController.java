package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.dto.request.WorkoutPlanRequest;
import com.modart00.fitness_coaching_system.dto.response.WorkoutPlanResponse;
import com.modart00.fitness_coaching_system.service.WorkoutPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @PostMapping("/users/{userId}")
    public WorkoutPlanResponse addForUser(@PathVariable Long userId,
                                          @Valid @RequestBody WorkoutPlanRequest request) {
        return workoutPlanService.saveForUser(userId, request);
    }

    @GetMapping("/me")
    public List<WorkoutPlanResponse> getMyPlans(Authentication authentication) {
        return workoutPlanService.getMyPlans(authentication);
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @GetMapping("/get/{id}")
    public WorkoutPlanResponse getById(@PathVariable Long id) {
        return workoutPlanService.findById(id);
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @GetMapping("/get/title/{title}")
    public WorkoutPlanResponse getByTitle(@PathVariable String title) {
        return workoutPlanService.findByTitle(title);
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @GetMapping("/get/all")
    public List<WorkoutPlanResponse> getAll() {
        return workoutPlanService.getAll();
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @PutMapping("/update/{id}")
    public WorkoutPlanResponse update(@PathVariable Long id,
                                      @Valid @RequestBody WorkoutPlanRequest request) {
        return workoutPlanService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        workoutPlanService.deleteById(id);
    }
}
