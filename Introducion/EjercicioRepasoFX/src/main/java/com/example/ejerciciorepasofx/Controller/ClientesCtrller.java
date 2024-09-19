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
        RadioButton seleccionado = (RadioButton) clientePremium.getSelectedToggle();
        if (contraseñaTxt.getText().isEmpty() || usuarioTxt.getText().isEmpty() || descuentoTxt.getText().isEmpty()
                || clientePremium.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos vacíos.");
            alert.setHeaderText("¡ERROR!");
            alert.setContentText("Por favor, rellene todos los datos antes de continuar.");
            alert.showAndWait();
        } else {
            double descuento = Double.parseDouble(descuentoTxt.getText());
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
        }
    } // METODO PARA INSERTAR UN NUEVO CLIENTE

    @FXML
    void onBuscarBtt(ActionEvent event) {
        if (usuarioTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos vacíos.");
            alert.setHeaderText("¡ERROR!");
            alert.setContentText("Por favor, rellene el nombre de usuario que desea buscar.");
            alert.showAndWait();
        } else {
            boolean semaforo = false; // atributo boolean para definir si ese usuario esta o no en la lista
            for (Usuario user : listaClientes) {
                if (user.getMailCl().equals(usuarioTxt.getText())) {
                    semaforo = true;
                    break;
                }
            }
            if (semaforo) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Usuario existente.");
                alert.setContentText("El usuario " + usuarioTxt.getText() + " esta registrado.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuario NO existente.");
                alert.setContentText("No se ha encontrado ningun usuario con ese mail");
                alert.showAndWait();
            } // if si el usuario se encuentra, se mostrara un mensaje
        }
    } // METODO PARA BUSCAR UN USUARIO POR MAIL

    @FXML
    void onIngresoBtt(ActionEvent event) {
        double totalIngresos = 0; // se guarda el total de ingresos segun su rol (si es premium o no)
        // restando su respectivo descuento establecido
        for (Usuario user : listaClientes) {
            if (user.isPremiumUs()) {
                totalIngresos = totalIngresos + 35.5 - user.getDescuentoCl();
            } else {
                totalIngresos = totalIngresos + 20.5 - user.getDescuentoCl();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Total.");
        alert.setContentText("El total de ingresos es de: " + totalIngresos + "€");
        alert.showAndWait();
    } // METODO PARA CALCULAR EL TOTAL DE INGRESOS DE LOS USUARIOS


    @FXML
    void onLimpiarBtt(ActionEvent event) {
        contraseñaTxt.clear();
        usuarioTxt.clear();
        descuentoTxt.clear();
        RadioButton seleccionado = (RadioButton) clientePremium.getSelectedToggle();
        seleccionado.setSelected(false);
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