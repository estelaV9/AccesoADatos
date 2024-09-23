package com.example.ejerciciorepasofx.Utilities;

import javafx.scene.control.Alert;

public class GeneralCode {
    public static void Alerts (String tipoAlert, String tituloAlert, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.valueOf(tipoAlert.toUpperCase()));
        alert.setTitle(tituloAlert);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    } // GENERAR ALERTA
}
