package com.fitness.controller;

import com.fitness.model.Food;
import com.fitness.repository.FoodRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fitness.controller.supportfunc.SupportClass.*;

@FieldDefaults(level = AccessLevel.PUBLIC)
@RestController
@RequestMapping("food")
@AllArgsConstructor
public class FoodController {

    private FoodRepository foodRepository;

    @GetMapping
    List<Food> allFood() {
        return foodRepository.findAll();
    }

    @GetMapping("{id:[\\d]+}")
    Food foodOnId(@PathVariable Long id) {
        Optional<Food> result = foodRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        return result.orElse(null);
    }

    @GetMapping(value = "getBy",
            params = {"id","dataType","desc" ,"foodCategoryId","publicationData"})
    List<Food> getByParam(@RequestParam("id") String id,//Can`t get request with Long
                                  @RequestParam("dataType") String dataType,
                                  @RequestParam("desc") String desc,
                                  @RequestParam("foodCategoryId") String catId,
                                  @RequestParam("publicationData") String pubData)
    {
        if (isSomeAlphaCascade(id,catId))
            return null;
        return foodRepository.findAll().stream()
                .filter(el -> eqWnullEx(el.getId(),id))
                .filter(el -> eqWnullEx(el.getDataType(),dataType))
                .filter(el -> eqWnullEx(el.getDescription(),desc))
                .filter(el -> eqWnullEx(el.getFoodCategoryId(),catId))
                .filter(el -> eqWnullEx(el.getFoodCategoryId(), pubData))
                .collect(Collectors.toList());
    }

    @PostMapping
    void addFood(@RequestBody Map<String,String> body)
    {
        if ( body.containsKey("foodCategoryId") && !StringUtils.isNumeric(body.get("foodCategoryId"))) return;
        Date date = null;
        if (body.containsKey("publicationData"))
            try
            {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                date = formatter.parse(body.get("publicationData"));
            }catch (ParseException pe){return;}
        Food food = new Food();
        body.computeIfPresent("dataType",(k,v)->{food.setDataType(v); return v;});
        body.computeIfPresent("desc",(k,v)->{food.setDescription(v); return v;});
        body.computeIfPresent("foodCategoryId",(k,v)->{food.setFoodCategoryId(pOnL(v)); return v;});
        food.setPublicationDate(date);
        foodRepository.save(food);
    }

    @DeleteMapping("{id:[\\d]+}")
    void removeFood(@PathVariable Long id)
    {
        Optional<Food> result = foodRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        result.ifPresent(food -> foodRepository.delete(food));
    }

    @PutMapping("{id:[\\d]+}")
    void updateFood(@PathVariable Long id,@RequestBody Map<String,String> body)
    {
        if (body.containsKey("foodCategoryId") && !StringUtils.isNumeric(body.get("foodCategoryId")))
            return;
        Food newVersion = foodRepository.findAll().stream().filter(el -> el.getId().equals(id)).findFirst().orElse(null);
        if (newVersion==null)
            return;
        Date date = null;
        if (body.containsKey("publicationData"))
            try
            {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                date = formatter.parse(body.get("publicationData"));
            }catch (ParseException pe){return;}

        body.computeIfPresent("dataType",(k,v)->{newVersion.setDataType(v); return v;});
        body.computeIfPresent("desc",(k,v)->{newVersion.setDescription(v); return v;});
        body.computeIfPresent("foodCategoryId",(k,v)->{newVersion.setFoodCategoryId(pOnL(v)); return v;});
        newVersion.setPublicationDate(date);
        foodRepository.save(newVersion);
    }
}
