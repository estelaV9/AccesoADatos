package com.example.multidbmanagerfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CocheCtrller {

    @FXML
    private Button cancelarBtt;

    @FXML
    private TableView<?> cochesTable;

    @FXML
    private Button eliminarBtt;

    @FXML
    private Button exitBtt;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> marcaCol;

    @FXML
    private TextField marcaTxt;

    @FXML
    private TableColumn<?, ?> matriculaCol;

    @FXML
    private TextField matriculaTxt;

    @FXML
    private TableColumn<?, ?> modeloCol;

    @FXML
    private TextField modeloTxt;

    @FXML
    private Button modificarBtt;

    @FXML
    private Button nuevoBtt;

    @FXML
    private TableColumn<?, ?> tipoCol;

    @FXML
    private ComboBox<?> tipoComboBox;

    @FXML
    private Button verMultasBtt;

    @FXML
    void onCancelarAction(ActionEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {

    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @FXML
    void onExitAction(ActionEvent event) {

    }

    @FXML
    void onModificarAction(ActionEvent event) {

    }

    @FXML
    void onNuevoCocheAction(ActionEvent event) {

    }

    @FXML
    void onVerMultasAction(ActionEvent event) {

    }

}