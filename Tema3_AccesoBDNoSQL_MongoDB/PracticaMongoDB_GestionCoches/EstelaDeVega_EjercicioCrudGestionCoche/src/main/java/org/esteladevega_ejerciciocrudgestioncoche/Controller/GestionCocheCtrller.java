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
    private ComboBox<?> tipoComboBox;
    @FXML
    private Button modificarBtt;
    @FXML
    private Button eliminarBtt;

    static MongoClient con;

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
        Coche coche = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), (String) tipoComboBox.getValue());
        CocheDAO.insertCar(con, coche);
    }
    String[] sfdsafdsa = {"2x2x2", "3x3x3", "4x4x4", "5x5x5", "6x6x6", "7x7x7",
            "PYRAMINX", "MEGAMINX", "SKEWB", "SQUARE-1", "CLOCK",
            "3x3x3 MIRROR", "PYRAMORPHIX", "MASTERMORPHIX"}; // ARRAY PARA GUARDAR LAS CATEGORIAS DE LOS CUBOS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = ConnectionDB.conectar();
        // INICIALIZAR LOS COMBOBOX
       // tipoComboBox.getItems().addAll(sfdsafdsa); // AÑADIR LOS VALORES AL COMBOBOX
    }
}
