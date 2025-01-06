package com.example.examen_java.daoimpl;

import com.example.examen_java.config.DatabaseConnection;
import com.example.examen_java.models.PlatPrincipal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatPrincipalDAO {
    private Connection connection;

    public PlatPrincipalDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public PlatPrincipal save(PlatPrincipal platPrincipal) {
        String query = "INSERT INTO PlatPrincipal (nom, description, prixDeBase) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, platPrincipal.getNom());
            stmt.setString(2, platPrincipal.getDescription());
            stmt.setDouble(3, platPrincipal.getPrixDeBase());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                platPrincipal.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platPrincipal;
    }

    public PlatPrincipal update(PlatPrincipal platPrincipal) {
        String query = "UPDATE PlatPrincipal SET nom = ?, description = ?, prixDeBase = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, platPrincipal.getNom());
            stmt.setString(2, platPrincipal.getDescription());
            stmt.setDouble(3, platPrincipal.getPrixDeBase());
            stmt.setInt(4, platPrincipal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platPrincipal;
    }

    public void delete(PlatPrincipal platPrincipal) {
        String query = "DELETE FROM PlatPrincipal WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, platPrincipal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlatPrincipal findById(int id) {
        String query = "SELECT * FROM PlatPrincipal WHERE id = ?";
        PlatPrincipal platPrincipal = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                platPrincipal = new PlatPrincipal(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getDouble("prixDeBase"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platPrincipal;
    }

    public List<PlatPrincipal> findAll() {
        String query = "SELECT * FROM PlatPrincipal";
        List<PlatPrincipal> platsPrincipaux = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                platsPrincipaux.add(new PlatPrincipal(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getDouble("prixDeBase")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platsPrincipaux;
    }
}

