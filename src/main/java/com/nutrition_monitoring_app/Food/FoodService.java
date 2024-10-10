package com.nutrition_monitoring_app.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition_monitoring_app.User.User;
import com.nutrition_monitoring_app.User.UserRepository;

@Service
public class FoodService { // Création des méthodes CRUD
    @Autowired // Dit à spring d'injecter le service dans cette variable
    private FoodRepository foodRepository;

    @Autowired
    private DefaultFoodRepository defaultFoodRepository;

    @Autowired
    private UserRepository userRepository;

    // Récupérer tous les aliments (par défaut et personnalisés)
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Récupérer tous les aliments d'un utilisateur (par défaut et personnalisés)
    public List<Food> getUserAllFoods(int userId) {
        return foodRepository.findByUserId(userId);
    }

    // Récupérer les aliments par défaut
    public List<DefaultFood> getDefaultFoods() {
        return defaultFoodRepository.findAll();
    }

    // Récupérer les aliments personnalisés d'un utilisateur
    public List<Food> getUserFoods(int userId) {
        return foodRepository.findByUserId(userId);
    }

    // Ajouter un nouvel aliment personnalisé pour un utilisateur
    public Food createFood(Food food, int userId) {
        // Vérifiez que l'utilisateur existe
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        
        // Définir les propriétés de l'aliment
        food.setUser(user); // Associer l'aliment à l'utilisateur
        food.setIsDefault(false); // Cet aliment est personnalisé
        
        // Sauvegarder l'aliment dans la base de données
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

    public List<Food> getUserFoodsByNameContainingIgnoreCase(int userId, String name) {
        return foodRepository.findByUserIdAndNameContainingIgnoreCase(userId, name);
    }

    public Optional<Food> getFoodById(int id) {
        return foodRepository.findById(id);
    }

    public List<DefaultFood> getDefaultFoodByNameIgnoreCase(String name) {
        return defaultFoodRepository.findByNameContainingIgnoreCase(name);
    }
}
