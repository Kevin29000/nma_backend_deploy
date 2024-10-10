package com.nutrition_monitoring_app.Food;

import jakarta.persistence.*;

@Entity
@Table (name = "default_foods")
public class DefaultFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories", nullable = false)
    private int calories;

    @Column(name = "proteins", nullable = false)
    private float proteins;

    @Column(name = "carbohydrates", nullable = false)
    private float carbohydrates;

    @Column(name = "lipids", nullable = false)
    private float lipids;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getLipids() {
        return lipids;
    }

    public void setLipids(float lipids) {
        this.lipids = lipids;
    }
}
