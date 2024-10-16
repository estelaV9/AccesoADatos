package org.esteladevega_ejerciciocrudgestioncoche.Controller;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.esteladevega_ejerciciocrudgestioncoche.Connection.ConnectionDB;
import org.esteladevega_ejerciciocrudgestioncoche.DAO.CocheDAO;
import org.esteladevega_ejerciciocrudgestioncoche.Model.Coche;

import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
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
    private TableView<?> cochesTable;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private Button modificarBtt;
    @FXML
    private Button eliminarBtt;

    static MongoClient con;
    String[] tipoCoches = {"Diesel", "Gasolina", "Electrico"};

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
        Coche coche = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
        CocheDAO.insertCar(con, coche);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = ConnectionDB.conectar();
        // INICIALIZAR LOS COMBOBOX
        tipoComboBox.getItems().addAll(tipoCoches); // AÑADIR LOS VALORES AL COMBOBOX
    }
}
