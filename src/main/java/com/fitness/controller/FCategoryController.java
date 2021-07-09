package com.fitness.controller;

import com.fitness.model.FoodCategory;
import com.fitness.repository.FCategoryRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fitness.utility.SupportClass.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("foodCategory")
@AllArgsConstructor
public class FCategoryController {

    FCategoryRepository foodCategoryRepository;

    @GetMapping
    public List<FoodCategory> allFoodCategory() {
        return foodCategoryRepository.findAll();
    }

    @GetMapping("{id:[\\d]+}")
    public FoodCategory foodCategoryOnId(@PathVariable Long id) {
        Optional<FoodCategory> result = foodCategoryRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        return result.orElse(null);
    }

    @GetMapping(value = "getBy", params = {"id", "code", "desc"})
    public List<FoodCategory> getByParam(@RequestParam("id") String id,//Can`t get request with Long
                                         @RequestParam("code") String code,
                                         @RequestParam("desc") String desc) {
        if (isSomeAlpha(id)) {
            return null;
        }
        return foodCategoryRepository.findAll().stream()
                .filter(el -> eqWnullEx(el.getId(), id))
                .filter(el -> eqWnullEx(el.getCode(), code))
                .filter(el -> eqWnullEx(el.getDescription(), desc))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addCategory(@RequestBody Map<String, String> body) {
        if (body.containsKey("code") && !StringUtils.isNumeric(body.get("code"))) {
            return;
        }
        FoodCategory foodCategory = new FoodCategory();
        body.computeIfPresent("foodId", (k, v) -> {
            foodCategory.setCode(pOnL(v));
            return v;
        });
        body.computeIfPresent("seqNum", (k, v) -> {
            foodCategory.setDescription(v);
            return v;
        });
        foodCategoryRepository.save(foodCategory);
    }

    @DeleteMapping("{id:[\\d]+}")
    public void removeCategory(@PathVariable Long id) {
        Optional<FoodCategory> result = foodCategoryRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        result.ifPresent(foodCategory -> foodCategoryRepository.delete(foodCategory));
    }

    @PutMapping("{id:[\\d]+}")
    public void updateCategory(@PathVariable Long id, @RequestBody Map<String, String> body) {
        if (body.containsKey("code") && !StringUtils.isNumeric(body.get("code")))
            return;
        FoodCategory newVersion = foodCategoryRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst().orElse(null);
        if (newVersion == null)
            return;
        body.computeIfPresent("foodId", (k, v) -> {
            newVersion.setCode(pOnL(v));
            return v;
        });
        body.computeIfPresent("seqNum", (k, v) -> {
            newVersion.setDescription(v);
            return v;
        });
        foodCategoryRepository.save(newVersion);
    }
}
