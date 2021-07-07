package com.Fitnes.Controller;

import com.Fitnes.model.FoodNutrient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FNutrientController {
    private JpaRepository<FoodNutrient, Long> fcr;
    @Autowired
    public FNutrientController(JpaRepository<FoodNutrient, Long> fcr) {
        this.fcr = fcr;
    }

    @GetMapping("/foodNutrient/all")
    public List<FoodNutrient> allFood() {
        return fcr.findAll();
    }
}
