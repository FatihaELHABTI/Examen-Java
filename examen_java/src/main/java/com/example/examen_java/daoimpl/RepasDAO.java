package com.example.examen_java.daoimpl;

import com.example.examen_java.config.DatabaseConnection;
import com.example.examen_java.models.Repas;
import com.example.examen_java.models.PlatPrincipal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepasDAO {

    private Connection connection;

    public RepasDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void create(Repas repas) {
        String sql = "INSERT INTO Repas (platPrincipal_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, repas.getPlatPrincipal().getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                repas.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Repas getById(int id) {
        String sql = "SELECT * FROM Repas WHERE id = ?";
        Repas repas = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int platPrincipalId = resultSet.getInt("platPrincipal_id");
                PlatPrincipalDAO platPrincipalDAO = new PlatPrincipalDAO();
                PlatPrincipal platPrincipal = platPrincipalDAO.findById(platPrincipalId);
                repas = new Repas(id, platPrincipal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repas;
    }

    public List<Repas> getAll() {
        List<Repas> repasList = new ArrayList<>();
        String sql = "SELECT * FROM Repas";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int platPrincipalId = resultSet.getInt("platPrincipal_id");
                PlatPrincipalDAO platPrincipalDAO = new PlatPrincipalDAO();
                PlatPrincipal platPrincipal = platPrincipalDAO.findById(platPrincipalId);
                repasList.add(new Repas(id, platPrincipal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repasList;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Repas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

