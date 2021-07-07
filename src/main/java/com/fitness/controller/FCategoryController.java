package com.fitness.controller;

import com.fitness.model.FoodCategory;
import com.fitness.repository.FCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FCategoryController {

    private FCategoryRepository repository;

    public FCategoryController(FCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/foodCategory/all")
    public List<FoodCategory> allFood() {
        return repository.findAll();
    }
}
