package org.esteladevega_crudcocheshibernate.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionCocheCtrller implements Initializable {
    @FXML
    private Button cancelarBtt;
    @FXML
    private Button guardarBtt;
    @FXML
    private TextField marcaTxt;
    @FXML
    private TextField matriculaTxt;
    @FXML
    private TextField modeloTxt;
    @FXML
    private Button nuevoBtt;
    @FXML
    private TableView cochesTable;
    @FXML
    private TableColumn tipoCol;
    @FXML
    private TableColumn modeloCol;
    @FXML
    private TableColumn matriculaCol;
    @FXML
    private TableColumn marcaCol;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private Button modificarBtt;
    @FXML
    private Button exitBtt;
    @FXML
    private Button eliminarBtt;

    String[] tipoCoches = {"Diesel", "Gasolina", "Electrico"}; // OPCIONES COMBOBOX

    @FXML
    void onExitAction() {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    } // SALIR DE LA APLICACIÓN

    @FXML
    void onCancelarAction() {

    } // LIMPIAR LOS CAMPOS DEL TEXTO Y EL COMBOBOX

    @FXML
    void onEliminarAction(ActionEvent event) {

    } // ELIMINAR UN COCHE

    @FXML
    void onModificarAction(ActionEvent event) {

    } // METODO PARA MODIFICAR LOS DATOS D EUN COCHE

    @FXML
    void onNuevoCocheAction(ActionEvent event) {

    } // CREAR UN COCHE NUEVO

    @FXML
    void onClickedTable(MouseEvent event) {
    } // CUANDO PULSA UN COCHE, SE SETTEAN LOS VALORES

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void refreshTable() {
        // PASAR LOS DATOS A LA TABLA CUANDO SE INICIE EL PROGRAMA
    } // ACTUALIZA LOS DATOS ACTUALES DE LA BASE DE DATOS
}