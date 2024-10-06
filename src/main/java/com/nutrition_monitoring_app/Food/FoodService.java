package com.nutrition_monitoring_app.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class FoodService { // Création des méthodes CRUD
    @Autowired // Dit à spring d'injecter le service dans cette variable
    private FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public Food updateFood(int id, Food newFoodDetails) {
        return foodRepository.findById(id)
            .map(food -> {
                food.setName(newFoodDetails.getName());
                food.setCalories(newFoodDetails.getCalories());
                food.setProteins(newFoodDetails.getProteins());
                food.setCarbohydrates(newFoodDetails.getCarbohydrates());
                food.setLipids(newFoodDetails.getLipids());
                return foodRepository.save(food);
            })
            .orElseThrow(() -> new RuntimeException("Food not found with id " + id));
    }

    public void deleteFood(int id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
        } else {
            throw new RuntimeException("Food not found with id " + id);
        }
    }
    
    public List<Food> getFoodByName(String name) {
        return foodRepository.findByNameContaining(name);
    }

    public List<Food> getFoodByNameIgnoreCase(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Food> getFoodById(int id) {
        return foodRepository.findById(id);
    }
}
