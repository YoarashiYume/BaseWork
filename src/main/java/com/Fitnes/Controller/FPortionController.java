package com.Fitnes.Controller;
import com.Fitnes.model.FoodPortion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class FPortionController {
    private JpaRepository<FoodPortion, Long> fr;

    @Autowired
    public FPortionController(JpaRepository<FoodPortion, Long> fr) {
        this.fr = fr;
    }

    @GetMapping("/foodPortion/all")
    public List<FoodPortion> allFood() {
        return fr.findAll();
    }
}
