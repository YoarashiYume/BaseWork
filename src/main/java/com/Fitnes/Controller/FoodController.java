package com.Fitnes.Controller;


import com.Fitnes.model.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Fitnes.model.Food;


import java.util.List;
@RestController
public class FoodController {
    private JpaRepository<Food, Long> fr;

    @Autowired
    public FoodController(JpaRepository<Food, Long> fr) {
        this.fr = fr;
    }

    @GetMapping("/food/all")
    public List<Food> allFood() {
        return fr.findAll();
    }
}
