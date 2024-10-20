package com.nutrition_monitoring_app.MealFood;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nutrition_monitoring_app.Meal.Meal;

@RestController
@RequestMapping("/api/meals/{mealId}/foods")
public class MealFoodController {

    @Autowired MealFoodService mealFoodService;

    // Récupérer tous les aliments d'un repas
    @GetMapping
    public List<MealFood> getFoodsForMeal(@PathVariable int mealId) {
        Meal meal = new Meal();
        meal.setId(mealId);
        return mealFoodService.getFoodsForMeal(meal);
    }

    @GetMapping("/{foodId}")
    public MealFood getFoodsForMealById(@PathVariable int mealId, @PathVariable int foodId) {
        Meal meal = new Meal();
        meal.setId(mealId);
        return mealFoodService.getMealFoodById(foodId);
    }

    @PostMapping
    public MealFood addFoodToMeal(@PathVariable int mealId, @RequestBody MealFood mealFood) {
        mealFood.setMeal(new Meal(mealId));
        return mealFoodService.addFoodToMeal(mealFood);
    }

    @DeleteMapping("/{foodId}")
    public void deleteMealFood(@PathVariable int mealId, @PathVariable int foodId) {
        mealFoodService.deleteMealFood(foodId);
    }

    // Méthode pour mettre à jour la quantité d'un aliment dans un repas
    @PutMapping("/{foodId}")
    public MealFood updateMealFoodQuantity(@PathVariable int mealId, @PathVariable int foodId, @RequestBody MealFood updatedMealFood) {
        MealFood existingMealFood = mealFoodService.getMealFoodById(foodId);
        
        if (existingMealFood != null && existingMealFood.getMeal().getId() == mealId) {
            existingMealFood.setQuantity(updatedMealFood.getQuantity());
            return mealFoodService.updateMealFood(existingMealFood);
        } else {
            throw new RuntimeException("L'aliment n'existe pas dans ce repas.");
        }
    }
}
