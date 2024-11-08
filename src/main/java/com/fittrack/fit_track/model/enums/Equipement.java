package com.fittrack.fit_track.model.enums;

public enum Equipement {
    AUCUN("Aucun équipement"),
    HALTERES("Haltères"),
    BARRE("Barre de musculation"),
    BANC("Banc de musculation"),
    ELASTIQUES("Élastiques"),
    MACHINE("Machine de musculation"),
    TAPIS("Tapis de sol");

    private final String libelle;

    Equipement(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
} 