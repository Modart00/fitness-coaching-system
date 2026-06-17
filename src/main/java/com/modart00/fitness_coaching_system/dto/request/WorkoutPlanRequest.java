package com.modart00.fitness_coaching_system.dto.request;

import com.modart00.fitness_coaching_system.entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutPlanRequest {

    private String title;

    private String description;

    private int durationWeek;

    private List<Exercise> exercises;

}
