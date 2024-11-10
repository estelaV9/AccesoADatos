package com.example.multidbmanagerfx.Utilities;

import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import javafx.scene.control.Alert;
import javax.swing.*;
import java.sql.SQLException;

public class StaticCode {
    public static void Alerts(String tipoAlert, String tituloAlert, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.valueOf(tipoAlert.toUpperCase()));
        alert.setTitle(tituloAlert);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    } // METODO ESTATICO PARA GENERA UNA ALERTA

    public static void exitApp() throws SQLException {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
            MySQL_ConnectionDB.desconectar();// CERRAR SESION
        }
    } // SALIR DE LA APLICACIÓN
}