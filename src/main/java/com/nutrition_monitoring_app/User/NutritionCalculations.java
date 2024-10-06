package com.nutrition_monitoring_app.User;

public class NutritionCalculations {
    private double bmr;
    private double targetCalories;
    private double proteins;
    private double carbohydrates;
    private double lipids;
    private double proteinsCalories;
    private double carbohydratesCalories;
    private double lipidsCalories;

    public NutritionCalculations(double bmr, double targetCalories, double proteins, double carbohydrates, double lipids) {
        this.bmr = bmr;
        this.targetCalories = targetCalories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.lipids = lipids;
        calculateMacroCalories();
    }

    private void calculateMacroCalories() {
        this.proteinsCalories = this.proteins * 4;
        this.carbohydratesCalories = this.carbohydrates * 4;
        this.lipidsCalories = this.lipids * 9;
    }

    public double getBmr() {
        return bmr;
    }

    public double getTargetCalories() {
        return targetCalories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getLipids() {
        return lipids;
    }

    public double getProteinsCalories() {
        return proteinsCalories;
    }

    public double getCarbohydratesCalories() {
        return carbohydratesCalories;
    }

    public double getLipidsCalories() {
        return lipidsCalories;
    }
}
