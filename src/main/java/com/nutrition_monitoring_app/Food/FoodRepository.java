package com.nutrition_monitoring_app.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByNameContaining(String name);
    List<Food> findByNameContainingIgnoreCase(String name);
}
