package com.Fitnes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Fitnes.model.Food;
import com.Fitnes.repo.FoodRepository;

import java.util.List;
@RestController
public class FoodController {
    private FoodRepository fr;

    @Autowired
    public FoodController(FoodRepository fr) {
        this.fr = fr;
    }

    @GetMapping("/food/all")
    public List<Food> allFood() {
        return fr.findAll();
    }
}
