package com.modart00.fitness_coaching_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutPlanRequest {

    @NotBlank
    private String title;

    private String description;

    @Positive
    private int durationWeek;

    @NotEmpty
    private List<Long> exerciseIds;

}
