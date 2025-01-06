package com.example.examen_java.controlleurs;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectionRepasController {

    @FXML
    private ListView<String> listViewRepas;

    @FXML
    public void initialize() {
        listViewRepas.getItems().addAll("Tajine de viande & Pruneaux", "Tajine de poulet & légumes");
    }

    public void goToChoixIngredients(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/examen_java/views/choix-ingredients.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
