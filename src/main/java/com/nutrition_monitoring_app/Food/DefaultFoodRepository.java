package com.nutrition_monitoring_app.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultFoodRepository extends JpaRepository<DefaultFood, Integer> {
    List<DefaultFood> findByNameContainingIgnoreCase(String name);
}
