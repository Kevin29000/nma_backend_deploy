package com.nutrition_monitoring_app.Meal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nutrition_monitoring_app.User.User;
import com.nutrition_monitoring_app.User.UserRepository;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired MealService mealService;

    @Autowired UserRepository userRepository;

    @GetMapping
    public List<Meal> getMeals(User user) {
        return mealService.getAllMeals(user);
    }

    @GetMapping("/user/{userId}")
    public List<Meal> getMealsByUserId(@PathVariable int userId) {
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + userId));
        
        return mealService.getAllMeals(user);
    }

    @PostMapping
    public Meal createMeal(@RequestBody Meal meal) {
        // Vérification de l'utilisateur
        User user = userRepository.findById(meal.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + meal.getUser().getId()));

        // Associer l'utilisateur au repas
        meal.setUser(user);

        return mealService.createMeal(meal);
    }

    @GetMapping("/{id}")
    public Meal getMeal(@PathVariable int id) {
        return mealService.getMealById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable int id) {
        mealService.deleteMeal(id);
    }

    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable int id, @RequestBody Meal updateMeal) {
        return mealService.updateMeal(id, updateMeal);
    }
}
