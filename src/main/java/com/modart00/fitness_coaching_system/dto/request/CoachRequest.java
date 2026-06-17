package com.modart00.fitness_coaching_system.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoachRequest {

    private String name;
    private String specialty;
    private int experienceYear;

}
