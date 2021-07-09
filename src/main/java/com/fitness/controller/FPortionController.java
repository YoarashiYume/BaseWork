package com.fitness.controller;

import com.fitness.model.FoodPortion;
import com.fitness.repository.FPortionRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.fitness.utility.SupportClass.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("foodPortion")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FPortionController {

    FPortionRepository portionRepository;

    @GetMapping
    public List<FoodPortion> allPortion() {
        return portionRepository.findAll();
    }

    @GetMapping("{id:[\\d]+}")
    public FoodPortion FoodPortionOnId(@PathVariable Long id) {
        Optional<FoodPortion> result = portionRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        return result.orElse(null);
    }

    @GetMapping(value = "getBy",
            params = {"id", "foodId", "seqNum", "amount", "measureUnitId", "portionDesc", "modifier", "gramWeight", "dataPoints", "footnote", "minYear"})
    public List<FoodPortion> getByParam(@RequestParam("id") String id,//Can`t get request with Long
                                 @RequestParam("foodId") String fId,
                                 @RequestParam("seqNum") String seqNum,
                                 @RequestParam("amount") String amount,
                                 @RequestParam("measureUnitId") String MUniId,
                                 @RequestParam("portionDesc") String pDesc,
                                 @RequestParam("modifier") String modifier,
                                 @RequestParam("gramWeight") String gWeight,
                                 @RequestParam("dataPoints") String dataPoints,
                                 @RequestParam("footnote") String footnote,
                                 @RequestParam("minYear") String minYear) {
        if (isSomeAlphaCascade(id, fId, seqNum, amount, dataPoints, minYear, MUniId, gWeight)) {
            return null;
        }
        return portionRepository.findAll().stream()
                .filter(el -> eqWnullEx(el.getId(), id))
                .filter(el -> eqWnullEx(el.getFdcId(), fId))
                .filter(el -> eqWnullEx(el.getSeqNum(), seqNum))
                .filter(el -> eqWnullEx(el.getAmount(), amount))
                .filter(el -> eqWnullEx(el.getDataPoints(), dataPoints))
                .filter(el -> eqWnullEx(el.getMeasureUnitId(), MUniId))
                .filter(el -> eqWnullEx(el.getPortionDescription(), pDesc))
                .filter(el -> eqWnullEx(el.getModifier(), modifier))
                .filter(el -> eqWnullEx(el.getGramWeight(), gWeight))
                .filter(el -> eqWnullEx(el.getFootnote(), footnote))
                .filter(el -> eqWnullEx(el.getMinYearAcquired() == null ? null : (long) el.getMinYearAcquired(), minYear))
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id:[\\d]+}")
    public void removeFoodPortion(@PathVariable Long id) {
        Optional<FoodPortion> result = portionRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        result.ifPresent(portionRepository::delete);
    }

    @PostMapping
    public void addFoodPortion(@RequestBody Map<String, String> body) {
        AtomicBoolean isCorrect = new AtomicBoolean(true);
        body.forEach((k, v) ->
        {
            if (!k.equals("portionDesc") && !k.equals("modifier"))
                if (isSomeAlphaCascade(v))
                    isCorrect.set(false);
        });
        if (!isCorrect.get()) { return; }
        FoodPortion foodPortion = new FoodPortion();
        body.computeIfPresent("foodId", (k, v) -> {
            foodPortion.setFdcId(pOnL(v));
            return v;
        });
        body.computeIfPresent("seqNum", (k, v) -> {
            foodPortion.setSeqNum(pOnL(v));
            return v;
        });
        body.computeIfPresent("amount", (k, v) -> {
            foodPortion.setAmount(pOnL(v));
            return v;
        });
        body.computeIfPresent("measureUnitId", (k, v) -> {
            foodPortion.setMeasureUnitId(pOnL(v));
            return v;
        });
        body.computeIfPresent("portionDesc", (k, v) -> {
            foodPortion.setPortionDescription(v);
            return v;
        });
        body.computeIfPresent("modifier", (k, v) -> {
            foodPortion.setModifier(v);
            return v;
        });
        body.computeIfPresent("gramWeight", (k, v) -> {
            foodPortion.setGramWeight(pOnD(v));
            return v;
        });
        body.computeIfPresent("dataPoints", (k, v) -> {
            foodPortion.setDataPoints(pOnL(v));
            return v;
        });
        body.computeIfPresent("footnote", (k, v) -> {
            foodPortion.setFootnote(pOnL(v));
            return v;
        });
        body.computeIfPresent("minYear", (k, v) -> {
            foodPortion.setMinYearAcquired(pOnL(v));
            return v;
        });
        portionRepository.save(foodPortion);
    }

    @PutMapping("{id:[\\d]*}")
    public void updateFoodPortion(@PathVariable Long id, @RequestBody Map<String, String> body) {
        AtomicBoolean isCorrect = new AtomicBoolean(true);
        body.forEach((k, v) ->
        {
            if (!k.equals("portionDesc") && !k.equals("modifier"))
                if (isSomeAlphaCascade(v))
                    isCorrect.set(false);
        });
        if (!isCorrect.get())
            return;

        Optional<FoodPortion> oldVersion = portionRepository.findAll().stream().filter(el -> id.equals(el.getId())).findFirst();
        if (oldVersion.isEmpty())
            return;
        FoodPortion newVersion = oldVersion.get();
        body.computeIfPresent("foodId", (k, v) -> {
            newVersion.setFdcId(pOnL(v));
            return v;
        });
        body.computeIfPresent("seqNum", (k, v) -> {
            newVersion.setSeqNum(pOnL(v));
            return v;
        });
        body.computeIfPresent("amount", (k, v) -> {
            newVersion.setAmount(pOnL(v));
            return v;
        });
        body.computeIfPresent("measureUnitId", (k, v) -> {
            newVersion.setMeasureUnitId(pOnL(v));
            return v;
        });
        body.computeIfPresent("portionDesc", (k, v) -> {
            newVersion.setPortionDescription(v);
            return v;
        });
        body.computeIfPresent("modifier", (k, v) -> {
            newVersion.setModifier(v);
            return v;
        });
        body.computeIfPresent("gramWeight", (k, v) -> {
            newVersion.setGramWeight(pOnD(v));
            return v;
        });
        body.computeIfPresent("dataPoints", (k, v) -> {
            newVersion.setDataPoints(pOnL(v));
            return v;
        });
        body.computeIfPresent("footnote", (k, v) -> {
            newVersion.setFootnote(pOnL(v));
            return v;
        });
        body.computeIfPresent("minYear", (k, v) -> {
            newVersion.setMinYearAcquired(pOnL(v));
            return v;
        });
        portionRepository.save(newVersion);
    }
}
