package com.example.examen_java.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // L'instance unique de la classe
    private static DatabaseConnection instance;

    private Connection connection;

    // URL de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/restaurantdb";
    private static final String USER = "root";
    private static final String PASSWORD = "7OCTober2023*";

    // Constructeur privé pour empêcher l'instanciation depuis l'extérieur
    private DatabaseConnection() {
        try {
            // Chargement du driver JDBC pour MySQL (nécessaire si vous utilisez une version ancienne de JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Création de la connexion à la base de données
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connexion à la base de données réussie !");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }

    // Méthode pour obtenir l'instance unique de la classe
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion
    public Connection getConnection() {
        return connection;
    }

    // Méthode pour fermer la connexion
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }
}
