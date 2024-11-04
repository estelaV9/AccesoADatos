package org.esteladevega_cochemultahibernate.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CocheCtrller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}