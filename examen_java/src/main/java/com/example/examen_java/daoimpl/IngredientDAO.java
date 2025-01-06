package com.example.examen_java.daoimpl;

import com.example.examen_java.config.DatabaseConnection;
import com.example.examen_java.models.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {

    private Connection connection;

    public IngredientDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void create(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredient (nom, quantite, unite) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ingredient.getNom());
            statement.setDouble(2, ingredient.getQuantite());
            statement.setString(3, ingredient.getUnite());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ingredient.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ingredient getById(int id) {
        String sql = "SELECT * FROM Ingredient WHERE id = ?";
        Ingredient ingredient = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                double quantite = resultSet.getDouble("quantite");
                String unite = resultSet.getString("unite");
                ingredient = new Ingredient(id, nom, quantite, unite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    public List<Ingredient> getAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM Ingredient";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                double quantite = resultSet.getDouble("quantite");
                String unite = resultSet.getString("unite");
                ingredients.add(new Ingredient(id, nom, quantite, unite));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Ingredient WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
