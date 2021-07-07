package com.fitness.repository;

import com.fitness.model.FoodPortion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FPortionRepository extends JpaRepository<FoodPortion, Long> {
}
