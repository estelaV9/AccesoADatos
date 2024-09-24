package com.example.ejerciciorepasofx.Controller;

import com.example.ejerciciorepasofx.Model.Usuario;
import com.example.ejerciciorepasofx.Utilities.GeneralCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javax.swing.*;
import java.util.ArrayList;

public class ClienteCtrller {
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
            // GENERAR ALERTA
            GeneralCode.Alerts("ERROR", "Campos vacíos.", "¡ERROR!", "Por favor, rellene todos los datos antes de continuar.");
        } else {
            try {
                double descuento = Double.parseDouble(descuentoTxt.getText());

                for (Usuario usu : listaClientes) {
                    if (usu.getMailCl().equals(usuarioTxt.getText())) {
                        semaforo = true;
                        break;
                    } else {
                        semaforo = false;
                    }
                } // for para buscar ese usuario

                if (semaforo) {
                    GeneralCode.Alerts("ERROR", "Usuario ya registrado.", "¡ERROR!",
                            "Ese usuario ya existe. Prueba con otro, por favor.");
                } // if si ese usuario si existe

                if (seleccionado.getText().equals("Si")) {
                    user = new Usuario(usuarioTxt.getText(), contraseñaTxt.getText(), descuento, true);
                    listaClientes.add(user);
                    GeneralCode.Alerts("CONFIRMATION", "Se añadió.", null,
                            "El usuario se ha añadido correctamente.");
                } else {
                    user = new Usuario(usuarioTxt.getText(), contraseñaTxt.getText(), descuento, false);
                    listaClientes.add(user);
                    GeneralCode.Alerts("CONFIRMATION", "Se añadió.", null,
                            "El usuario se ha añadido correctamente.");
                } // if para si el usuario es premium o no
            } catch (NumberFormatException e) {
                GeneralCode.Alerts("ERROR", "Descuento no válido.", "¡ERROR!",
                        "Por favor, introduzca un número válido para el descuento.");
            } // EXCEPCION PARA QUE EL USUARIO NO PONGA UNA LETRA EN EL DESCUENTO
        }
    } // METODO PARA INSERTAR UN NUEVO CLIENTE

    @FXML
    void onBuscarBtt(ActionEvent event) {
        if (usuarioTxt.getText().isEmpty()) {
            GeneralCode.Alerts("ERROR", "Campos vacíos.", "¡ERROR!",
                    "Por favor, rellene el nombre de usuario que desea buscar.");
        } else {
            boolean semaforo = false; // atributo boolean para definir si ese usuario esta o no en la lista
            for (Usuario user : listaClientes) {
                if (user.getMailCl().equals(usuarioTxt.getText())) {
                    semaforo = true;
                    break;
                }
            }
            if (semaforo) {
                GeneralCode.Alerts("CONFIRMATION", "Usuario existente.",
                        null, "El usuario " + usuarioTxt.getText() + " esta registrado.");
            } else {
                GeneralCode.Alerts("INFORMATION", "Usuario NO existente.",
                        null, "No se ha encontrado ningún usuario con ese mail.");
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
        GeneralCode.Alerts("INFORMATION", "Total.",
                null, "El total de ingresos es de: " + totalIngresos + "€");
    } // METODO PARA CALCULAR EL TOTAL DE INGRESOS DE LOS USUARIOS


    @FXML
    void onLimpiarBtt(ActionEvent event) {
        contraseñaTxt.clear();
        usuarioTxt.clear();
        descuentoTxt.clear();
        clientePremium.selectToggle(null); // DESELECCIONAR OPCION DEL TOGGLEGROUP
    } // LIMPIAR CAMPOS

    @FXML
    void onSalirBtt(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    } // SALIR DE LA APLICACIÓN
}