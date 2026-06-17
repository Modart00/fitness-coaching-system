package com.modart00.fitness_coaching_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoachResponse {

    private String name;
    private String specialty;
    private int experienceYear;

}
