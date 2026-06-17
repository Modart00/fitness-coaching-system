package com.modart00.fitness_coaching_system.dto.response;

import com.modart00.fitness_coaching_system.entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutPlanResponse {

    private String title;

    private String description;

    private int durationWeek;

    private List<Exercise> exercises;

}
