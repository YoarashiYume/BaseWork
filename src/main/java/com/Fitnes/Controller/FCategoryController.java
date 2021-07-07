package com.Fitnes.Controller;

import com.Fitnes.model.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FCategoryController {
    private JpaRepository<FoodCategory, Long> fcr;
    @Autowired
    public FCategoryController(JpaRepository<FoodCategory, Long> fcr) {
        this.fcr = fcr;
    }

    @GetMapping("/foodCategory/all")
    public List<FoodCategory> allFood() {
        return fcr.findAll();
    }
}
