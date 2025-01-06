package com.example.examen_java.models;

import java.util.List;

public class Repas {
    private int id;
    private PlatPrincipal platPrincipal;
    private List<Ingredient> ingredients;
    private List<Supplement> supplements;

    // Constructeur
    public Repas(int id, PlatPrincipal platPrincipal, List<Ingredient> ingredients, List<Supplement> supplements) {
        this.id = id;
        this.platPrincipal = platPrincipal;
        this.ingredients = ingredients;
        this.supplements = supplements;
    }
    public Repas(int id, PlatPrincipal platPrincipal) {
        this.id = id;
        this.platPrincipal = platPrincipal;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public PlatPrincipal getPlatPrincipal() { return platPrincipal; }
    public void setPlatPrincipal(PlatPrincipal platPrincipal) { this.platPrincipal = platPrincipal; }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    public List<Supplement> getSupplements() { return supplements; }
    public void setSupplements(List<Supplement> supplements) { this.supplements = supplements; }

    // Calcul du prix total du repas
    public double calculerPrixTotal() {
        double total = platPrincipal.calculerPrixTotal();
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getQuantite(); // Peut être ajusté selon les prix des ingrédients
        }
        for (Supplement supplement : supplements) {
            total += supplement.getPrix();
        }
        return total;
    }
}
