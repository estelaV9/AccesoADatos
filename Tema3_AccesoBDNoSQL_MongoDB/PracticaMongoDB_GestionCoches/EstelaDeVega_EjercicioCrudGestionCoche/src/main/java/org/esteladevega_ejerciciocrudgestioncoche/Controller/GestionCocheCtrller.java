package org.esteladevega_ejerciciocrudgestioncoche.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GestionCocheCtrller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}