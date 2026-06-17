package com.modart00.fitness_coaching_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRequest {
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Integer setCount;

    @NotNull
    @Positive
    private Integer repCount;
}
