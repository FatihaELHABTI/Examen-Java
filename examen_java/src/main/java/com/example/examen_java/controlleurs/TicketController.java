package com.example.examen_java.controlleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketController {
    @FXML
    private Label ticketLabel;

    @FXML
    public void initialize() {
        ticketLabel.setText("Bienvenue Ali baba\nTotal: 125.24");
    }
}
