package com.fittrack.fit_track.model.enums;

public enum PartieCorps {
    BRAS("Bras"),
    JAMBES("Jambes"),
    DOS("Dos"),
    POITRINE("Poitrine"),
    EPAULES("Épaules"),
    ABDOMINAUX("Abdominaux"),
    CORPS_ENTIER("Corps entier");

    private final String libelle;

    PartieCorps(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
} 