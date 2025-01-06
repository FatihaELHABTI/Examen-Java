package com.example.examen_java.console;

import com.example.examen_java.models.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création du client
        Client client = new Client(1, "Ali", "baba", "ali.baba@example.com");

        // Création des ingrédients pour le premier repas
        Ingredient viande = new Ingredient(1, "Viande", 250, "gramme");
        Ingredient pruneaux = new Ingredient(2, "Pruneaux", 1, "gramme");

        List<Ingredient> ingredientsRepas1 = new ArrayList<>();
        ingredientsRepas1.add(viande);
        ingredientsRepas1.add(pruneaux);

        // Création des suppléments pour le premier repas
        Supplement frites = new Supplement(1, "Frites", 11.0);
        Supplement boisson = new Supplement(2, "Boisson", 12.0);

        List<Supplement> supplementsRepas1 = new ArrayList<>();
        supplementsRepas1.add(frites);
        supplementsRepas1.add(boisson);

        // Création du premier repas
        PlatPrincipal plat1 = new PlatPrincipal(1, "Tajine de viande & Pruneaux", "Un délicieux tajine", 50.0);
        Repas repas1 = new Repas(1, plat1, ingredientsRepas1, supplementsRepas1);

        // Création des ingrédients pour le deuxième repas
        Ingredient poisson = new Ingredient(3, "Poisson", 250, "gramme");
        Ingredient carrote = new Ingredient(4, "Carrote", 1, "gramme");
        Ingredient pommeDeTerre = new Ingredient(5, "Pomme de terre", 1, "gramme");
        Ingredient olive = new Ingredient(6, "Olive", 1, "gramme");

        List<Ingredient> ingredientsRepas2 = new ArrayList<>();
        ingredientsRepas2.add(poisson);
        ingredientsRepas2.add(carrote);
        ingredientsRepas2.add(pommeDeTerre);
        ingredientsRepas2.add(olive);

        // Création des suppléments pour le deuxième repas
        Supplement jusOrange = new Supplement(3, "Jus d'orange", 13.0);
        Supplement saladeMarocaine = new Supplement(4, "Salade marocaine", 14.0);

        List<Supplement> supplementsRepas2 = new ArrayList<>();
        supplementsRepas2.add(jusOrange);
        supplementsRepas2.add(saladeMarocaine);

        // Création du deuxième repas
        PlatPrincipal plat2 = new PlatPrincipal(2, "Tajine de poulet & légumes", "Un tajine savoureux", 60.0);
        Repas repas2 = new Repas(2, plat2, ingredientsRepas2, supplementsRepas2);

        // Création de la commande avec la date
        List<Repas> listeRepas = new ArrayList<>();
        listeRepas.add(repas1);
        listeRepas.add(repas2);

        LocalDate dateCommande = LocalDate.now(); // Date actuelle
        Commande commande = new Commande(1, dateCommande, client, listeRepas);

        // Affichage du ticket
        System.out.println("Bienvenue " + client.getNom() + " " + client.getPrenom());
        System.out.println("----------------------------------------");
        System.out.println("-------------TICKET-----------------------");
        System.out.println("Nom: " + client.getNom() + " " + client.getPrenom());
        System.out.println("Date: " + dateCommande);
        System.out.println("\nnombre de repas: " + listeRepas.size());

        int index = 1;
        for (Repas repas : listeRepas) {
            System.out.println("Repas N°:" + index++ + " " + repas.getPlatPrincipal().getNom());
            System.out.println("Ingrédient:");
            for (Ingredient ingredient : repas.getIngredients()) {
                System.out.println(ingredient.getNom() + ": " + ingredient.getQuantite() + " " + ingredient.getUnite());
            }
            System.out.println("Suppléments:");
            for (Supplement supplement : repas.getSupplements()) {
                System.out.println(supplement.getNom() + " " + supplement.getPrix());
            }
            System.out.println("********");
        }

        System.out.println("\n-------Total:" + commande.calculerPrixTotal());
        System.out.println("----------------------------------------");
    }
}
