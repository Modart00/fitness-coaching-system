package com.modart00.fitness_coaching_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressRequest {
    @NotNull
    @Positive
    private Double currentWeight;

    private String note;
}
