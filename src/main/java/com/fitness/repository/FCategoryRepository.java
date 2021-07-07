package com.fitness.repository;

import com.fitness.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
