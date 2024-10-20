package com.nutrition_monitoring_app.MealFood;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutrition_monitoring_app.Meal.Meal;

public interface MealFoodRepository extends JpaRepository<MealFood, Integer> {
    List<MealFood> findByMeal(Meal meal);
}
