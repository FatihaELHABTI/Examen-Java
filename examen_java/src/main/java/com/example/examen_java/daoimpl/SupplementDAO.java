package com.example.examen_java.daoimpl;

import com.example.examen_java.config.DatabaseConnection;
import com.example.examen_java.models.Supplement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAO {

    private Connection connection;

    public SupplementDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void create(Supplement supplement) {
        String sql = "INSERT INTO Supplement (nom, prix) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, supplement.getNom());
            statement.setDouble(2, supplement.getPrix());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                supplement.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Supplement getById(int id) {
        String sql = "SELECT * FROM Supplement WHERE id = ?";
        Supplement supplement = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prix");
                supplement = new Supplement(id, nom, prix);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplement;
    }

    public List<Supplement> getAll() {
        List<Supplement> supplements = new ArrayList<>();
        String sql = "SELECT * FROM Supplement";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prix");
                supplements.add(new Supplement(id, nom, prix));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplements;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Supplement WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
