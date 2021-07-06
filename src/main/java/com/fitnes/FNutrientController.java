package com.Fitnes;
import com.Fitnes.model.FoodCategory;
import com.Fitnes.model.FoodNutrient;
import com.Fitnes.repo.FNutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FNutrientController {
    private FNutrientRepository fcr;
    @Autowired
    public FNutrientController(FNutrientRepository fcr) {
        this.fcr = fcr;
    }

    @GetMapping("/foodNutrient/all")
    public List<FoodNutrient> allFood() {
        return fcr.findAll();
    }
}
