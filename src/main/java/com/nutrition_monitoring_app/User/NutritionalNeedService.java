package com.nutrition_monitoring_app.User;

import org.springframework.stereotype.Service;

@Service
public class NutritionalNeedService {

    public NutritionCalculations calculateNutrition(User user) {
        double bmr = calculateBMR(user);
        double tdee = calculateTDEE(bmr, user.getActivity());
        double targetCalories = adjustCaloriesForGoal(tdee, user.getGoal());

        double proteins = targetCalories * 0.20 / 4;
        double carbohydrates = targetCalories * 0.50 / 4;
        double lipids = targetCalories * 0.30 / 9;

        return new NutritionCalculations(bmr, targetCalories, proteins, carbohydrates, lipids);
    }

    private double calculateBMR(User user) {
        int age = java.time.LocalDate.now().getYear() - user.getBirthdate().getYear();
        
        if (user.getGender() == Gender.MASCULIN) {
            return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * age) + 5;
        } else {
            return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * age) - 161;
        }
    }

    private double calculateTDEE(double bmr, Activity activity) {
        switch (activity) {
            case SEDENTARY:
                    return bmr * 1.2;
            case LIGHTLY_ACTIVE:
                return bmr * 1.375;
            case MODERATELY_ACTIVE:
                return bmr * 1.55;
            case VERY_ACTIVE:
                return bmr * 1.725;
            case EXTREMELY_ACTIVE:
                return bmr * 1.9;
            default:
                return bmr;
        }
    }

    private double adjustCaloriesForGoal(double tdee, Goal goal) {
        switch (goal) {
            case WEIGHT_LOSS:
                return tdee * 0.85;
            case WEIGHT_MAINTENANCE:
                return tdee;
            case MASS_GAIN:
                return tdee * 1.1;
            default:
                return tdee;
        }
    }
}
