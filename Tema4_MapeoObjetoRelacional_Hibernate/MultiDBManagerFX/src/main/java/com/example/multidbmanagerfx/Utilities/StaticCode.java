package com.example.multidbmanagerfx.Utilities;

import com.example.multidbmanagerfx.App;
import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import com.example.multidbmanagerfx.Controller.MultaCtrller;
import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Model.Multa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javax.swing.*;
import javax.swing.plaf.synth.SynthTableHeaderUI;
import java.io.IOException;
import java.net.URL;
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

    public static void changeViewBtt(String nameFxml, Button button, String title) {
        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new
                    FXMLLoader(App.class.getResource(nameFxml));
                    // FXMLLoader(R.getUI(nameFxml));
            Parent root = fxmlLoader.load();

            // OBTENER CONTROLLER
            Object controller = fxmlLoader.getController();

            Scene scene = new Scene(root); // CREAR UNA NUEVA ESCENA

            /*// CARGAR Y APLICAR LA HOJA DE ESTILOS
            URL cssURL = R.getStyleSheet("Styles.css");
            if (cssURL != null) {
                scene.getStylesheets().add(cssURL.toExternalForm());
            }*/

            // OBTENER EL STAGE ACTUAL A PARTIR DEL BOTON QUE SE HA CLICADO
            Stage stage = (Stage) button.getScene().getWindow();
            //Stage stage = new Stage(); // HACE OTRO STAGE
            stage.setTitle(title); // TITULO DE LA VENTANA
            stage.setScene(scene); // ESTABLECER LA NUEVA ESCENA AL STAGE ACTUAL

            if (!stage.isShowing()) {
                stage.show();
            } // MOSTRAR VENTANA SI NO ESTA VISIBLE
        } catch (IOException e) {
            e.printStackTrace(); // SI HAY ERROR EN LA CARGA DEL FXML, SE LANZA LA EXCEPCION
        }
    } // METODO ESTATICO PARA CAMBIAR DE VISTA CON UN ID DE UN BOTON

    public static void changeViewWithPharamsBtt(String nameFxml, Button button, ToggleGroup dbGroup, String title, Coche coche) {
        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new
                    FXMLLoader(App.class.getResource(nameFxml));
            // FXMLLoader(R.getUI(nameFxml));
            Parent root = fxmlLoader.load();

            // CREAMOS UNA INSTANCIA CONTROLLER AL QUE VAMOS A PASAR DATOS
            MultaCtrller controller = fxmlLoader.getController();
            controller.displayCoche(coche, dbGroup);

            Scene scene = new Scene(root); // CREAR UNA NUEVA ESCENA

            // OBTENER EL STAGE ACTUAL A PARTIR DEL BOTON QUE SE HA CLICADO
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setTitle(title); // TITULO DE LA VENTANA
            stage.setScene(scene); // ESTABLECER LA NUEVA ESCENA AL STAGE ACTUAL

            if (!stage.isShowing()) {
                stage.show();
            } // MOSTRAR VENTANA SI NO ESTA VISIBLE
        } catch (IOException e) {
            e.printStackTrace(); // SI HAY ERROR EN LA CARGA DEL FXML, SE LANZA LA EXCEPCION
        }
    } // METODO ESTATICO PARA CAMBIAR DE VISTA PASANDOLE PARAMETROS A UN CONTROLADOR
}