package org.esteladevega_ejerciciocrudgestioncoche.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionCocheCtrller {
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
    private TableView<?> cochesTable;
    @FXML
    private ComboBox<?> tipoComboBox;
    @FXML
    private Button modificarBtt;
    @FXML
    private Button eliminarBtt;

    @FXML
    void onCancelarAction(ActionEvent event) {
        matriculaTxt.clear();
        marcaTxt.clear();
        modeloTxt.clear();
        tipoComboBox.setValue(null);
    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @FXML
    void onGuardarAction(ActionEvent event) {

    }

    @FXML
    void onModificarAction(ActionEvent event) {

    }

    @FXML
    void onNuevoCocheAction(ActionEvent event) {

    }

}
