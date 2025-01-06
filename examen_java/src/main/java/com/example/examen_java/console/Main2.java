package com.example.examen_java.console;

import com.example.examen_java.models.*;
import com.example.examen_java.daoimpl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        // Création du client
        Client client = new Client("Ali", "baba", "ali.baba@email.com");
        ClientDAO clientDAO = new ClientDAO();
        client = clientDAO.save(client);

        // Création des plats principaux
        PlatPrincipal tajineViande = new PlatPrincipal(1, "Tajine de viande & Pruneaux", "Tajine traditionnel", 50.0);
        PlatPrincipal tajinePoulet = new PlatPrincipal(2, "Tajine de poulet & légumes", "Tajine au poulet", 45.0);
        PlatPrincipalDAO platPrincipalDAO = new PlatPrincipalDAO();
        tajineViande = platPrincipalDAO.save(tajineViande);
        tajinePoulet = platPrincipalDAO.save(tajinePoulet);

        // Création des ingrédients pour le premier repas
        List<Ingredient> ingredientsRepas1 = new ArrayList<>();
        ingredientsRepas1.add(new Ingredient(1, "Viande", 250, "gramme"));
        ingredientsRepas1.add(new Ingredient(2, "Pruneaux", 1, "gramme"));

        // Création des ingrédients pour le deuxième repas
        List<Ingredient> ingredientsRepas2 = new ArrayList<>();
        ingredientsRepas2.add(new Ingredient(3, "Poisson", 250, "gramme"));
        ingredientsRepas2.add(new Ingredient(4, "Carrote", 1, "gramme"));
        ingredientsRepas2.add(new Ingredient(5, "Pomme de terre", 1, "gramme"));
        ingredientsRepas2.add(new Ingredient(6, "Olive", 1, "gramme"));

        // Création des suppléments pour le premier repas
        List<Supplement> supplementsRepas1 = new ArrayList<>();
        supplementsRepas1.add(new Supplement(1, "Frites", 11.0));
        supplementsRepas1.add(new Supplement(2, "Boisson", 12.0));

        // Création des suppléments pour le deuxième repas
        List<Supplement> supplementsRepas2 = new ArrayList<>();
        supplementsRepas2.add(new Supplement(3, "Jus d'orange", 13.0));
        supplementsRepas2.add(new Supplement(4, "Salade marocaine", 14.0));

        // Création des repas
        Repas repas1 = new Repas(1, tajineViande, ingredientsRepas1, supplementsRepas1);
        Repas repas2 = new Repas(2, tajinePoulet, ingredientsRepas2, supplementsRepas2);

        // Création de la liste des repas
        List<Repas> repas = new ArrayList<>();
        repas.add(repas1);
        repas.add(repas2);

        // Création de la commande
        Commande commande = new Commande(1, LocalDate.now(), client, repas);
        CommandeDAO commandeDAO = new CommandeDAO();
        commande = commandeDAO.save(commande);

        // Génération du ticket
        genererTicket(commande);
    }

    private static void genererTicket(Commande commande) {
        System.out.println("Bienvenue " + commande.getClient().getNom() + " " + commande.getClient().getPrenom());
        System.out.println("----------------------------------------");
        System.out.println("-------------TICKET----------------------");
        System.out.println("Nom: " + commande.getClient().getNom() + " " + commande.getClient().getPrenom());
        System.out.println();
        System.out.println("nombre de repas: " + commande.getRepas().size());

        int numeroRepas = 1;
        for (Repas repas : commande.getRepas()) {
            System.out.println("Repas N°:" + numeroRepas + " " + repas.getPlatPrincipal().getNom());
            System.out.println("Ingrédient:");
            for (Ingredient ingredient : repas.getIngredients()) {
                System.out.println(ingredient.getNom() + ": " + ingredient.getQuantite() + " " + ingredient.getUnite());
            }
            System.out.println("Suppléments:");
            for (Supplement supplement : repas.getSupplements()) {
                System.out.println(supplement.getNom() + " " + supplement.getPrix());
            }
            System.out.println("********");
            numeroRepas++;
        }

        System.out.println();
        System.out.println("-------Total:" + commande.calculerPrixTotal());
        System.out.println("----------------------------------------");
    }
}