package com.example.examen_java.daoimpl;

import com.example.examen_java.config.DatabaseConnection;
import com.example.examen_java.models.Client;
import com.example.examen_java.models.Commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {
    private Connection connection;

    public CommandeDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Commande save(Commande commande) {
        String query = "INSERT INTO Commande (date, client_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(commande.getDate()));
            stmt.setInt(2, commande.getClient().getId());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                commande.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    public Commande update(Commande commande) {
        String query = "UPDATE Commande SET date = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(commande.getDate()));
            stmt.setInt(2, commande.getClient().getId());
            stmt.setInt(3, commande.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    public void delete(Commande commande) {
        String query = "DELETE FROM Commande WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, commande.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Commande findById(int id) {
        String query = "SELECT * FROM Commande WHERE id = ?";
        Commande commande = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new ClientDAO().findById(rs.getInt("client_id"));
                commande = new Commande(rs.getInt("id"), rs.getDate("date").toLocalDate(), client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    public List<Commande> findAll() {
        String query = "SELECT * FROM Commande";
        List<Commande> commandes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = new ClientDAO().findById(rs.getInt("client_id"));
                commandes.add(new Commande(rs.getInt("id"), rs.getDate("date").toLocalDate(), client));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
}
