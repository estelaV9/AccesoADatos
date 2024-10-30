package org.esteladevega_crudcocheshibernate.StaticCode;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
}