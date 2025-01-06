package com.example.examen_java.models;

import java.time.LocalDate;
import java.util.List;

public class Commande {
    private int id;
    private LocalDate date;  // Ajout de la date de la commande
    private Client client;
    private List<Repas> repas;

    // Constructeur
    public Commande(int id, LocalDate date, Client client, List<Repas> repas) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.repas = repas;
    }
    // Constructeur
    public Commande(int id, LocalDate date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Repas> getRepas() { return repas; }
    public void setRepas(List<Repas> repas) { this.repas = repas; }

    // Calcul du total de la commande
    public double calculerPrixTotal() {
        double total = 0;
        for (Repas repas : repas) {
            total += repas.calculerPrixTotal();
        }
        return total;
    }
}
