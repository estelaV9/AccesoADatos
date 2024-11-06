package org.esteladevega_cochemultahibernate.Utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.esteladevega_cochemultahibernate.App;
import org.hibernate.Session;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class StaticCode {
    public static void Alerts(String tipoAlert, String tituloAlert, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.valueOf(tipoAlert.toUpperCase()));
        alert.setTitle(tituloAlert);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    } // METODO ESTATICO PARA GENERA UNA ALERTA

    public static boolean camposVacios(ComboBox<?> combobox, TextField... textFields) {
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return false;
            } // SI ESTA VACIO UN TEXTFIELD DEVUELVE FALSE
        } // RECORRE LOS TEXTFIELDS

        // VERIFICAR SI HAY VALOR EN EL COMBOBOX
        return combobox.getSelectionModel().getSelectedItem() != null;
    } // METODO PARA COMPROBAR CAMPOS VACIOS

    public static void cambiarVistaBtt(String nameFxml, Button button, String title) {
        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new
                    FXMLLoader(App.class.getResource(nameFxml));
            Parent root = fxmlLoader.load();
            // OBTENER CONTROLLER
            Object controller = fxmlLoader.getController();
            Scene scene = new Scene(root); // CREAR UNA NUEVA ESCENA
            // OBTENER EL STAGE ACTUAL A PARTIR DEL BOTON QUE SE HA CLICADO
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setTitle(title); // TITULO DE LA VENTANA
            stage.setScene(scene); // ESTABLECER LA NUEVA ESCENA AL STAGE ACTUAL
            // MOSTRAR VENTANA SI NO ESTA VISIBLE
            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace(); // SI HAY ERROR EN LA CARGA DEL FXML, SE LANZA LA EXCEPCION
        }
    } // METODO ESTATICO PARA CAMBIAR DE VISTA CON UN ID DE UN BOTON

    public static void exitApp(Session session) throws SQLException {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
            session.close(); // CERRAR SESION
        }
    } // SALIR DE LA APLICACIÓN
}