package com.Fitnes;

import com.Fitnes.model.FoodCategory;
import com.Fitnes.repo.FCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FCategoryController {
    private FCategoryRepository fcr;
    @Autowired
    public FCategoryController(FCategoryRepository fcr) {
        this.fcr = fcr;
    }

    @GetMapping("/foodCategory/all")
    public List<FoodCategory> allFood() {
        return fcr.findAll();
    }
}
