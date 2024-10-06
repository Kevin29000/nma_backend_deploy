package com.nutrition_monitoring_app.User;

public enum Activity {
    SEDENTARY("Sédentaire (peu ou pas d'exercice)"),
    LIGHTLY_ACTIVE("Légèrement actif (1-3 jours d'exercice)"),
    MODERATELY_ACTIVE("Modérément actif (3-5 jours d'exercice)"),
    VERY_ACTIVE("Très actif (6-7 jours d'exercice)"),
    EXTREMELY_ACTIVE("Extrêmement actif (emploi physique)");

    private final String label;

    Activity(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
