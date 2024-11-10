package com.example.multidbmanagerfx.Controller;

import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CocheCtrller implements Initializable {
    @FXML
    private Button cancelarBtt;
    @FXML
    private TableView<Coche> cochesTable;
    @FXML
    private Button eliminarBtt;
    @FXML
    private Button exitBtt;
    @FXML
    private TableColumn<Integer,Coche> idCol;
    @FXML
    private TableColumn<String, Coche> marcaCol;
    @FXML
    private TextField marcaTxt;
    @FXML
    private TableColumn<String, Coche> matriculaCol;
    @FXML
    private TextField matriculaTxt;
    @FXML
    private TableColumn<String, Coche> modeloCol;
    @FXML
    private TextField modeloTxt;
    @FXML
    private Button modificarBtt;
    @FXML
    private Button nuevoBtt;
    @FXML
    private TableColumn<String, Coche> tipoCol;
    @FXML
    private ComboBox<String> tipoComboBox;
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
    void onExitAction(ActionEvent event) throws SQLException {
        StaticCode.exitApp();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = MySQL_ConnectionDB.conectar();
    }
}