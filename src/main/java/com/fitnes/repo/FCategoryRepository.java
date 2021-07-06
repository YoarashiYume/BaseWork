package com.Fitnes.repo;
import com.Fitnes.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FCategoryRepository extends JpaRepository<FoodCategory, Long>{
}
