package com.nutrition_monitoring_app.User;

public enum Goal {
    WEIGHT_LOSS("Perte de poids"),
    WEIGHT_MAINTENANCE("Maintien du poids"),
    MASS_GAIN("Prise de masse");

    private final String label;

    Goal(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
