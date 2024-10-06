package com.nutrition_monitoring_app.User;

public enum Gender {
    MASCULIN("Masculin"),
    FEMININ("Féminin");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
