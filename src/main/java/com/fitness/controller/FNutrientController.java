package com.fitness.controller;

import com.fitness.model.FoodNutrient;
import com.fitness.repository.FNutrientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FNutrientController {

    private FNutrientRepository repository;

    public FNutrientController(FNutrientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/foodNutrient/all")
    public List<FoodNutrient> allFood() {
        return repository.findAll();
    }
}
