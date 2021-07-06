package com.Fitnes.repo;

import com.Fitnes.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodRepository extends JpaRepository<Food, Long> {
}