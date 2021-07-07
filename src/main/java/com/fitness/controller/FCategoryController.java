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
@FieldDefaults(level = AccessLevel.PUBLIC)
@RestController
@RequestMapping("foodCategory")
@AllArgsConstructor
public class FCategoryController {
    private FCategoryRepository fcr;

    @GetMapping
    List<FoodCategory> allFoodCategory() {
        return fcr.findAll();
    }
    @GetMapping("{id:[\\d]*}")
    FoodCategory foodCategoryOnId(@PathVariable Long id) {
        Optional<FoodCategory> result = fcr.findAll().stream().filter(el -> el.getId().equals(id)).findFirst();
        return result.orElse(null);
    }

    @GetMapping(value = "getBy",
    params = {"id","code", "desc"})
    List<FoodCategory> getByParam(@RequestParam("id") String id,//Can`t get request with Long
                                         @RequestParam("code") String code,
                                         @RequestParam("desc") String desc)
    {
        return fcr.findAll().stream().filter(el -> el.getId().equals(!StringUtils.isNumeric(id)? el.getId() : Long.parseLong(id,10)))
                .filter(el -> el.getCode().equals(!StringUtils.isNumeric(code)? el.getCode() : Long.parseLong(code,10)))
                .filter(el -> el.getDescription().equals(desc.isEmpty() ? el.getDescription() : desc))
                .collect(Collectors.toList());
    }
    @PostMapping
    void addCategory(@RequestBody Map<String,String> body)
    {
        if (body.size()!=2 || !body.containsKey("code")
                || !body.containsKey("desc") || !StringUtils.isNumeric(body.get("code"))) return;
        FoodCategory fc = new FoodCategory();
        fc.setCode(Long.parseLong(body.get("code"),10));
        fc.setDescription(body.get("desc"));
        fcr.save(fc);
    }
    @DeleteMapping("{id:[\\d]*}")
    void removeCategory(@PathVariable Long id)
    {
        Optional<FoodCategory> result = fcr.findAll().stream().filter(el -> el.getId().equals(id)).findFirst();
        result.ifPresent(foodCategory -> fcr.delete(foodCategory));
    }
    @PutMapping("{id:[\\d]*}")
    void updateCategory(@PathVariable Long id,@RequestBody Map<String,String> body)
    {
        if (body.size()<1 )
            return;
        Optional<FoodCategory> oldVersion = fcr.findAll().stream().filter(el -> el.getId().equals(id)).findFirst();
        if (fcr.findAll().size()<=id || oldVersion.isEmpty())
            return;
        FoodCategory newVersion = oldVersion.get();
        if (body.containsKey("code") && StringUtils.isNumeric(body.get("code")))
            newVersion.setCode(Long.parseLong(body.get("code"),10));
        if (body.containsKey("desc"))
            newVersion.setDescription(body.get("desc"));
        fcr.save(newVersion);
    }
}
