package com.example.examen_java.models;

public class Ingredient {
    private int id;
    private String nom;
    private double quantite;
    private String unite;

    // Constructeur
    public Ingredient(int id, String nom, double quantite, String unite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.unite = unite;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getQuantite() { return quantite; }
    public void setQuantite(double quantite) { this.quantite = quantite; }

    public String getUnite() { return unite; }
    public void setUnite(String unite) { this.unite = unite; }
}

