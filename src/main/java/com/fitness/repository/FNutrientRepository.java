package com.fitness.repository;

import com.fitness.model.FoodNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FNutrientRepository extends JpaRepository<FoodNutrient, Long> {
}
