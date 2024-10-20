package com.nutrition_monitoring_app.Meal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutrition_monitoring_app.User.User;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findByUser(User user);
}
