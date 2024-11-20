package org.esteladevega_examen1evaluacion.Utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.esteladevega_examen1evaluacion.Controller.JugadorCtrller;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import java.io.IOException;

public class StaticCode {

    public static void Alerts(String tipoAlert, String tituloAlert, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.valueOf(tipoAlert.toUpperCase()));
        alert.setTitle(tituloAlert);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    } // METODO ESTATICO PARA GENERA UNA ALERTA

    public static void changeViewBtt(String nameFxml, Button button, Equipo equipo, String title) {
        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new
                    FXMLLoader(R.getUI(nameFxml));
            Parent root = fxmlLoader.load();

            // CREAMOS UNA INSTANCIA CONTROLLER AL QUE VAMOS A PASAR DATOS
            JugadorCtrller controller = fxmlLoader.getController();
            controller.displayController(equipo);
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
    } // METODO ESTATICO PARA CAMBIAR DE VISTA CON UN ID DE UN BOTON
}