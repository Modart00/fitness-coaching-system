package com.modart00.fitness_coaching_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressResponse {
    private Long id;
    private Double currentWeight;
    private String note;
    private LocalDate createdAt;
}
