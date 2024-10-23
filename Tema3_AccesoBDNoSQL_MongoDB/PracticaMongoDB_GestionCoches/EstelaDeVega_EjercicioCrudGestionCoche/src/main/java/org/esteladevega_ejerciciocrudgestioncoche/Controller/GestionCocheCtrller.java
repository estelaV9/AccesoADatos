package org.esteladevega_ejerciciocrudgestioncoche.Controller;

import com.google.gson.Gson;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
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
    private TableView<Coche> cochesTable;
    @FXML
    private TableColumn<String, Coche> tipoCol;
    @FXML
    private TableColumn<String, Coche> modeloCol;
    @FXML
    private TableColumn<String, Coche> matriculaCol;
    @FXML
    private TableColumn<String, Coche> marcaCol;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private Button modificarBtt;
    @FXML
    private Button eliminarBtt;

    static MongoClient con;
    public static MongoCollection<Document> collection;
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
        CocheDAO.insertCar(coche);
        refreshTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = ConnectionDB.conectar();
        // OBTENER LA BASE DE DATOS DESDE LA CONEXION
        MongoDatabase database = con.getDatabase("Coches");

        // OBTENER LA COLECCION LLAMADA 'Coches' DE LA DB
        collection = database.getCollection("Coches");

        // INICIALIZAR LOS COMBOBOX
        tipoComboBox.getItems().addAll(tipoCoches); // AÑADIR LOS VALORES AL COMBOBOX

        refreshTable();
    }


    public void refreshTable() {
        // PASAR LOS DATOS A LA TABLA CUANDO SE INICIE EL PROGRAMA
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        cochesTable.setItems(CocheDAO.listCar()); // ESTABLECER LISTA
    }
}
