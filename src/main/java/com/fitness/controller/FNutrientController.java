package com.fitness.controller;

import com.fitness.model.FoodNutrient;
import com.fitness.repository.FNutrientRepository;

import static com.fitness.utility.SupportClass.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("foodNutrient")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FNutrientController {

    FNutrientRepository nutrientRepository;

    @GetMapping
    public List<FoodNutrient> allFoodNutrient() {
        return nutrientRepository.findAll();
    }

    @GetMapping("{id:[\\d]+}")
    public FoodNutrient foodNutrientOnId(@PathVariable Long id) {
        Optional<FoodNutrient> result = nutrientRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        return result.orElse(null);
    }

    @GetMapping(value = "getBy", params = {"id", "foodId", "nutrientId", "amount", "dataPoints", "derivationId", "min", "max", "median", "footnote", "minYear"})
    public List<FoodNutrient> getByParam(@RequestParam("id") String id,//Can`t get request with Long
                                         @RequestParam("foodId") String fId,
                                         @RequestParam("nutrientId") String nId,
                                         @RequestParam("amount") String amount,
                                         @RequestParam("dataPoints") String dataPoints,
                                         @RequestParam("derivationId") String dervId,
                                         @RequestParam("min") String min,
                                         @RequestParam("max") String max,
                                         @RequestParam("median") String median,
                                         @RequestParam("footnote") String footnote,
                                         @RequestParam("minYear") String minYear) {
        if (isSomeAlphaCascade(id, fId, nId, amount, dataPoints, minYear, min, max, median, dervId)) {
            return null;
        }
        return nutrientRepository.findAll().stream()
                .filter(el -> eqWnullEx(el.getId(), id))
                .filter(el -> eqWnullEx(el.getFdcId(), fId))
                .filter(el -> eqWnullEx(el.getNutrientId(), nId))
                .filter(el -> eqWnullEx(el.getAmount(), amount))
                .filter(el -> eqWnullEx(el.getDataPoints(), dataPoints))
                .filter(el -> eqWnullEx(el.getDerivationId(), dervId))
                .filter(el -> eqWnullEx(el.getMin(), min))
                .filter(el -> eqWnullEx(el.getMax(), max))
                .filter(el -> eqWnullEx(el.getMedian(), median))
                .filter(el -> eqWnullEx(el.getFootnote(), footnote))
                .filter(el -> eqWnullEx(el.getMinYearAcqured() == null ? null : (long) el.getMinYearAcqured(), minYear))
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id:[\\d]+}")
    public void removeFoodNutrient(@PathVariable Long id) {
        Optional<FoodNutrient> result = nutrientRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        result.ifPresent(nutrientRepository::delete);
    }

    @PostMapping
    public void addFoodNutrient(@RequestBody Map<String, String> body) {
        AtomicBoolean isCorrect = new AtomicBoolean(true);
        body.forEach((k, v) ->
        {
            if (!k.equals("footnote"))
                if (isSomeAlphaCascade(v))
                    isCorrect.set(false);
        });
        if (!isCorrect.get())
            return;
        FoodNutrient foodNutrient = new FoodNutrient();
        body.computeIfPresent("foodId", (k, v) -> {
            foodNutrient.setFdcId(pOnL(v));
            return v;
        });
        body.computeIfPresent("nutrientId", (k, v) -> {
            foodNutrient.setNutrientId(pOnL(v));
            return v;
        });
        body.computeIfPresent("amount", (k, v) -> {
            foodNutrient.setAmount(pOnL(v));
            return v;
        });
        body.computeIfPresent("dataPoints", (k, v) -> {
            foodNutrient.setDataPoints(pOnL(v));
            return v;
        });
        body.computeIfPresent("derivationId", (k, v) -> {
            foodNutrient.setDerivationId(pOnL(v));
            return v;
        });
        body.computeIfPresent("min", (k, v) -> {
            foodNutrient.setMin(pOnD(v));
            return v;
        });
        body.computeIfPresent("max", (k, v) -> {
            foodNutrient.setMax(pOnD(v));
            return v;
        });
        body.computeIfPresent("median", (k, v) -> {
            foodNutrient.setMedian(pOnD(v));
            return v;
        });
        body.computeIfPresent("minYear", (k, v) -> {
            foodNutrient.setMinYearAcqured(pOnL(body.get("minYear")) == null ? null : (pOnL(body.get("minYear")).intValue()));
            return v;
        });
        body.computeIfPresent("footnote", (k, v) -> {
            foodNutrient.setFootnote(v);
            return v;
        });
        nutrientRepository.save(foodNutrient);
    }

    @PutMapping("{id:[\\d]+}")
    public void updateFoodNutrient(@PathVariable Long id, @RequestBody Map<String, String> body) {
        AtomicBoolean isCorrect = new AtomicBoolean(true);
        body.forEach((k, v) -> {
            if (!k.equals("footnote"))
                if (isSomeAlphaCascade(v))
                    isCorrect.set(false);
        });
        if (!isCorrect.get()) { return; }

        FoodNutrient newVersion = nutrientRepository.findAll().stream().filter(el -> el.getId().equals(id)).findFirst().orElse(null);
        if (newVersion == null) { return; }
        body.computeIfPresent("foodId", (k, v) -> {
            newVersion.setFdcId(pOnL(v));
            return v;
        });
        body.computeIfPresent("nutrientId", (k, v) -> {
            newVersion.setNutrientId(pOnL(v));
            return v;
        });
        body.computeIfPresent("amount", (k, v) -> {
            newVersion.setAmount(pOnL(v));
            return v;
        });
        body.computeIfPresent("dataPoints", (k, v) -> {
            newVersion.setDataPoints(pOnL(v));
            return v;
        });
        body.computeIfPresent("derivationId", (k, v) -> {
            newVersion.setDerivationId(pOnL(v));
            return v;
        });
        body.computeIfPresent("min", (k, v) -> {
            newVersion.setMin(pOnD(v));
            return v;
        });
        body.computeIfPresent("max", (k, v) -> {
            newVersion.setMax(pOnD(v));
            return v;
        });
        body.computeIfPresent("median", (k, v) -> {
            newVersion.setMedian(pOnD(v));
            return v;
        });
        body.computeIfPresent("minYear", (k, v) -> {
            newVersion.setMinYearAcqured(pOnL(body.get("minYear")) == null ? null : (pOnL(body.get("minYear")).intValue()));
            return v;
        });
        body.computeIfPresent("footnote", (k, v) -> {
            newVersion.setFootnote(v);
            return v;
        });
        nutrientRepository.save(newVersion);
    }
}
