package com.nutrition_monitoring_app.Food;

import com.nutrition_monitoring_app.User.User;

import jakarta.persistence.*;

@Entity
@Table (name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "default_food_id")
    private DefaultFood defaultFood;

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

    @Column(name="is_default", nullable = false)
    private boolean isDefault;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DefaultFood getDefaultFood() {
        return defaultFood;
    }

    public void setDefaultFood(DefaultFood defaultFood) {
        this.defaultFood = defaultFood;
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
