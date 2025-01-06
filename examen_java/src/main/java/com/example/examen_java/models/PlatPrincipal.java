package com.example.examen_java.models;

public class PlatPrincipal {
    private int id;
    private String nom;
    private String description;
    private double prixDeBase;

    // Constructeur
    public PlatPrincipal(int id, String nom, String description, double prixDeBase) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prixDeBase = prixDeBase;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrixDeBase() { return prixDeBase; }
    public void setPrixDeBase(double prixDeBase) { this.prixDeBase = prixDeBase; }

    // Calcul du prix total du plat (avec ingrédients et suppléments)
    public double calculerPrixTotal() {
        // Ce calcul pourrait être étendu en fonction des ingrédients et suppléments
        return prixDeBase;
    }
}
