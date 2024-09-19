package com.example.ejerciciorepasofx.Controller;

import com.example.ejerciciorepasofx.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.util.ArrayList;

public class ClientesCtrller {
    @FXML
    private ToggleGroup clientePremium;
    @FXML
    private TextField contraseñaTxt;
    @FXML
    private TextField descuentoTxt;
    @FXML
    private TextField usuarioTxt;

    ArrayList<Usuario> listaClientes = new ArrayList<>();

    @FXML
    void onAñadirBtt(ActionEvent event) {
        boolean semaforo = false;
        Usuario user;
        double descuento = Double.parseDouble(descuentoTxt.getText());
        RadioButton seleccionado = (RadioButton) clientePremium.getSelectedToggle();
        if (contraseñaTxt.getText().isEmpty() || usuarioTxt.getText().isEmpty() || descuentoTxt.getText().isEmpty()
                || seleccionado.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos vacíos.");
            alert.setHeaderText("¡ERROR!");
            alert.setContentText("Por favor, rellene todos los datos antes de continuar.");
            alert.showAndWait();
        } else
            do {
                for (Usuario usua : listaClientes) {
                    if (usua.getMailCl().equals(usuarioTxt.getText())) {
                        semaforo = true;
                        break;
                    } else {
                        semaforo = false;
                    }
                } // for para buscar ese usuario
                if (semaforo) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Usuario ya registrado.");
                    alert.setHeaderText("¡ERROR!");
                    alert.setContentText("Ese usuario ya existe. Prueba con otro, por favor.");
                    alert.showAndWait();
                } // if si ese usuario si existe
            } while (semaforo); // bucle para repetir el mail hasta que no este registrado


        if (seleccionado.getText().equals("Si")) {
            user = new Usuario(usuarioTxt.getText(), contraseñaTxt.getText(), descuento, true);
            listaClientes.add(user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Se añadió.");
            alert.setContentText("El usuario se ha añadido correctamente.");
            alert.showAndWait();
        } else {
            user = new Usuario(usuarioTxt.getText(), contraseñaTxt.getText(), descuento, false);
            listaClientes.add(user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Se añadió.");
            alert.setContentText("El usuario se ha añadido correctamente.");
            alert.showAndWait();
        } // if para si el usuario es premium o no
    } // METODO PARA INSERTAR UN NUEVO CLIENTE

    @FXML
    void onBuscarBtt(ActionEvent event) {

    }

    @FXML
    void onIngresoBtt(ActionEvent event) {

    }

    @FXML
    void onLimpiarBtt(ActionEvent event) {
        contraseñaTxt.clear();
        usuarioTxt.clear();
        descuentoTxt.clear();
    }

    @FXML
    void onSalirBtt(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    } // SALIR DE LA APLICACIÓN
}