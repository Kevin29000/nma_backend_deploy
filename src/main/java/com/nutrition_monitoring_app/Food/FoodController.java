package com.nutrition_monitoring_app.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired // Dit à spring d'injecter le service dans cette variable
    private FoodService foodService;

    @GetMapping // Get consulte
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id)
            .orElseThrow(() -> new RuntimeException("Food not found with id " + id));
    }

    @PostMapping // Post envoie ?!
    public Food createFood(@RequestBody Food food) {
        return foodService.createFood(food);
    }

    @PutMapping("/{id}") // Put met à jour un élément existant
    public Food updateFood(@PathVariable int id, @RequestBody Food newFoodDetails) { // Pathariable pour passer l'id dans {id}
        return foodService.updateFood(id, newFoodDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/search")
    public List<Food> getFoodByName(@RequestParam String name) { // Resquest Param pour le passer dans le /search
        return foodService.getFoodByName(name);
    }

    @GetMapping("/search/ignore-case")
    public List<Food> getFoodByNameIgnoreCase(@RequestParam String name) {
        return foodService.getFoodByNameIgnoreCase(name);
    }
}
