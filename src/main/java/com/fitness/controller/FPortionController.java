package com.fitness.controller;

import com.fitness.model.FoodPortion;
import com.fitness.repository.FPortionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FPortionController {

    private FPortionRepository repository;

    public FPortionController(FPortionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/foodPortion/all")
    public List<FoodPortion> allFood() {
        return repository.findAll();
    }
}
