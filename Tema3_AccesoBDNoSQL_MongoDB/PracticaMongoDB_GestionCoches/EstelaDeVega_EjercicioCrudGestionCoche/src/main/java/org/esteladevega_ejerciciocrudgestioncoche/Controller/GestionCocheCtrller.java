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
import org.esteladevega_ejerciciocrudgestioncoche.Utilities.StaticCode;

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
    private TableColumn<Coche, String> tipoCol;
    @FXML
    private TableColumn<Coche, String> modeloCol;
    @FXML
    private TableColumn<Coche, String> matriculaCol;
    @FXML
    private TableColumn<Coche, String> marcaCol;
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
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem();
        // COMPROBAR SI EL USUARIO HA SELECCIONADO UN COCHE PARA MODIFICAR
        if (seleccionada != null) {
            CocheDAO.deleteCar(seleccionada.getMatricula());
            StaticCode.Alerts("INFORMATION", "Eliminar Coche", "INFORMATION",
                    "Se ha eliminado los datos del coche correctamente.");
        } else {
            StaticCode.Alerts("ERROR", "Coche vacio", "¡ERROR!",
                    "Por favor, seleccione un coche para eliminar.");
        } // coche vacio

        refreshTable(); // ACTUALIZAR LA TABLA
    }

    @FXML
    void onModificarAction(ActionEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem();
        // COMPROBAR SI EL USUARIO HA SELECCIONADO UN COCHE PARA MODIFICAR
        if (seleccionada != null) {
            // COMPROBAR SI NO HAY CAMPOS VACIOS
            if (StaticCode.camposVacios(tipoComboBox, matriculaTxt, marcaTxt, modeloTxt)) {
                // COMPROBAR SI LA MATRICULA QUE SE VA A MODIFICAR YA ESTA EN USO
                if (!CocheDAO.estaMatricula(matriculaTxt.getText())) {
                    Coche cocheModificar = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
                    CocheDAO.modifyCar(seleccionada.getMatricula(), cocheModificar);
                    StaticCode.Alerts("INFORMATION", "Modificar Coche", "INFORMATION",
                            "Se ha modificado los datos del coche correctamente.");
                } else {
                    StaticCode.Alerts("ERROR", "Error al modificar", "¡ERROR!",
                            "Ya existe esa matricula.");
                } // matricula en uso
            } else {
                StaticCode.Alerts("ERROR", "Campo vacio", "¡ERROR!",
                        "Por favor, rellene todos los datos del formulario.");
            } // campos vacios
        } else {
            StaticCode.Alerts("ERROR", "Coche vacio", "¡ERROR!",
                    "Por favor, seleccione un coche para eliminar.");
        } // coche vacio

        refreshTable(); // ACTUALIZAR LA TABLA
    } // METODO PARA MODIFICAR LOS DATOS D EUN COCHE

    @FXML
    void onNuevoCocheAction(ActionEvent event) {
        // COMPROBAR CAMPOS VACIOS, SI HAY ALGUN CAMPO VACIO SALTA UN ERROR
        if (StaticCode.camposVacios(tipoComboBox, marcaTxt, matriculaTxt, modeloTxt)) {
            Coche coche = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
            CocheDAO.insertCar(coche);
            refreshTable();
        } else {
            StaticCode.Alerts("ERROR", "Campo vacio", "¡ERROR!",
                    "Por favor, rellene todos los datos del formulario.");
        }
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
