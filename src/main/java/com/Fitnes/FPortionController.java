package com.Fitnes;
import com.Fitnes.model.FoodPortion;
import com.Fitnes.repo.FPortionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class FPortionController {
    private FPortionRepository fr;

    @Autowired
    public FPortionController(FPortionRepository fr) {
        this.fr = fr;
    }

    @GetMapping("/foodPortion/all")
    public List<FoodPortion> allFood() {
        return fr.findAll();
    }
}
