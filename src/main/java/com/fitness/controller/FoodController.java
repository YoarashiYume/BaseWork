package com.fitness.controller;

import com.fitness.model.Food;
import com.fitness.repository.FoodRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    private FoodRepository repository;

    public FoodController(FoodRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/food/all")
    public List<Food> allFood() {
        return repository.findAll();
    }
}
