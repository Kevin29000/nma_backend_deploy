package com.nutrition_monitoring_app.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired // Dit à spring d'injecter le service dans cette variable
    private FoodService foodService;

    // Récupérer tous les aliments (personnalisés + par défaut)
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    // Récupérer tous les aliments (personnalisés et par défaut) pour un utilisateur
    @GetMapping("/user-foods/{userId}")
    public List<Food> getUserAllFoods(@PathVariable int userId) {
        return foodService.getUserAllFoods(userId);
    }


    // Récupérer les aliments par défaut
    @GetMapping("/default-foods") 
    public List<DefaultFood> getDefaultFoods() {
        return foodService.getDefaultFoods();
    }

    // Récupérer les aliments personnalisés d'un utilisateur
    @GetMapping("/user-foods-personalized/{userId}")
    public List<Food> getUserFoods(@PathVariable int userId) {
        return foodService.getUserFoods(userId);
    }

    // Récupérer un aliment par son ID
    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id)
            .orElseThrow(() -> new RuntimeException("Food not found with id " + id));
    }

    // Ajouter un aliment personnalisé
    @PostMapping("user/{userId}")
    public Food createFood(@RequestBody Food food, @PathVariable int userId) {
        return foodService.createFood(food, userId);
    }

    // Mettre à jour un aliment personnalisé
    @PutMapping("/{id}")
    public Food updateFood(@PathVariable int id, @RequestBody Food newFoodDetails) {
        return foodService.updateFood(id, newFoodDetails);
    }

    // Supprimer un aliment
    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFood(id);
    }

     // Rechercher des aliments par nom (personnalisés et par défaut)
    @GetMapping("/search")
    public List<Food> getFoodByName(@RequestParam String name) { // Resquest Param pour le passer dans le /search
        return foodService.getFoodByName(name);
    }

    // Rechercher des aliments par défaut par nom (sans distinction de casse)
    @GetMapping("/search/ignore-case")
    public List<Food> getFoodByNameIgnoreCase(@RequestParam String name) {
        return foodService.getFoodByNameIgnoreCase(name);
    }

    // Rechercher des aliments par défaut par nom et id de l'utilisateur (sans distinction de casse)
    @GetMapping("/user-foods/search")
    public List<Food> getUserFoodsByNameContainingIgnoreCase(@RequestParam int userId, @RequestParam String name) {
        return foodService.getUserFoodsByNameContainingIgnoreCase(userId, name);
    }
}
