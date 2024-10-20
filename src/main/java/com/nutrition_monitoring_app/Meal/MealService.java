package com.nutrition_monitoring_app.Meal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition_monitoring_app.User.User;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getAllMeals(User user) {
        return mealRepository.findByUser(user);
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal getMealById(int mealId) {
        return mealRepository.findById(mealId).orElse(null);
    }

    public void deleteMeal(int mealId) {
        mealRepository.deleteById(mealId);
    }

    public Meal updateMeal(int mealId, Meal updateMeal) {
        Meal existingMeal = mealRepository.findById(mealId).orElse(null);
        if (existingMeal != null) {
            existingMeal.setName(updateMeal.getName());
            existingMeal.setDate(updateMeal.getDate());

            return mealRepository.save(existingMeal);
        }
        return null;
    }
}
