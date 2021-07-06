package com.Fitnes.repo;

import com.Fitnes.model.FoodNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FNutrientRepository extends JpaRepository<FoodNutrient, Long>{
}
