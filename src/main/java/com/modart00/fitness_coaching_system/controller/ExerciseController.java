package com.modart00.fitness_coaching_system.controller;

import com.modart00.fitness_coaching_system.dto.request.ExerciseRequest;
import com.modart00.fitness_coaching_system.dto.request.RegisterRequest;
import com.modart00.fitness_coaching_system.dto.response.ExerciseResponse;
import com.modart00.fitness_coaching_system.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @PostMapping("/add")
    public ExerciseResponse add(@Valid @RequestBody ExerciseRequest request){
        return exerciseService.save(request);
    }

    @GetMapping("/get/{id}")
    public ExerciseResponse getById(@PathVariable Long id){
        return exerciseService.findById(id);
    }


    @GetMapping("/get/all")
    public List<ExerciseResponse> getAll(){
      return  exerciseService.getAll();
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @PutMapping("/update/{id}")
    public ExerciseResponse update(@PathVariable Long id,@Valid @RequestBody ExerciseRequest request){
        return exerciseService.update(id,request);
    }

    @PreAuthorize("hasAnyRole('COACH','ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        exerciseService.deleteById(id);
    }





}
