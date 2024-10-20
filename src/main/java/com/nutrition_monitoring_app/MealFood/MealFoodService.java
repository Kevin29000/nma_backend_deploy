package com.nutrition_monitoring_app.MealFood;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition_monitoring_app.Meal.Meal;

@Service
public class MealFoodService {

    @Autowired
    private MealFoodRepository mealFoodRepository;

    public List<MealFood> getFoodsForMeal(Meal meal) {
        return mealFoodRepository.findByMeal(meal);
    }

    public MealFood addFoodToMeal(MealFood mealFood) {
        return mealFoodRepository.save(mealFood);
    }

    public void deleteMealFood(int mealFoodId) {
        mealFoodRepository.deleteById(mealFoodId);
    }

    public MealFood getMealFoodById(int mealFoodId) {
        return mealFoodRepository.findById(mealFoodId).orElse(null);
    }
    
    public MealFood updateMealFood(MealFood mealFood) {
        return mealFoodRepository.save(mealFood);
    }
}
