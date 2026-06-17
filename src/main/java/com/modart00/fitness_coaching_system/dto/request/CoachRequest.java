package com.modart00.fitness_coaching_system.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String specialty;

    @PositiveOrZero
    private int experienceYear;

}
