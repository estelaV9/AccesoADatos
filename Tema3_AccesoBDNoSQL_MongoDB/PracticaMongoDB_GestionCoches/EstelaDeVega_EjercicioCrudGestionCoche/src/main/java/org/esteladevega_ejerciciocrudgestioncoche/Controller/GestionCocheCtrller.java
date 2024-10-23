package org.esteladevega_ejerciciocrudgestioncoche.Controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
    private Button exitBtt;
    @FXML
    private Button eliminarBtt;

    static MongoClient con; // CONEXION
    public static MongoCollection<Document> collection; // COLECCION
    String[] tipoCoches = {"Diesel", "Gasolina", "Electrico"}; // OPCIONES COMBOBOX

    @FXML
    void onExitAction() {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
            ConnectionDB.desconectar(con); // DESPUES DE SALIR DE LA APLICACION, DESCONECTAMOS LA CONEXION
        }
    } // SALIR DE LA APLICACIÓN

    @FXML
    void onCancelarAction() {
        matriculaTxt.clear();
        marcaTxt.clear();
        modeloTxt.clear();
        tipoComboBox.setValue(null);
    } // LIMPIAR LOS CAMPOS DEL TEXTO Y EL COMBOBOX

    @FXML
    void onEliminarAction(ActionEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        // COMPROBAR SI EL USUARIO HA SELECCIONADO UN COCHE PARA MODIFICAR
        if (seleccionada != null) {
            CocheDAO.deleteCar(seleccionada.getMatricula()); // SE ELIMINA EL COCHE
            StaticCode.Alerts("INFORMATION", "Eliminar Coche", "INFORMATION",
                    "Se ha eliminado los datos del coche correctamente.");
            refreshTable(); // ACTUALIZAR LA TABLA
        } else {
            StaticCode.Alerts("ERROR", "Coche vacio", "¡ERROR!",
                    "Por favor, seleccione un coche para eliminar.");
        } // coche vacio
    } // ELIMINAR UN COCHE

    @FXML
    void onModificarAction(ActionEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        // COMPROBAR SI EL USUARIO HA SELECCIONADO UN COCHE PARA MODIFICAR
        if (seleccionada != null) {
            // COMPROBAR SI NO HAY CAMPOS VACIOS
            if (StaticCode.camposVacios(tipoComboBox, matriculaTxt, marcaTxt, modeloTxt)) {
                // COMPROBAR SI LA MATRICULA QUE SE VA A MODIFICAR YA ESTA EN USO
                if (!CocheDAO.estaMatricula(matriculaTxt.getText(), seleccionada.getMatricula())) {
                    Coche cocheModificar = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
                    CocheDAO.modifyCar(seleccionada.getMatricula(), cocheModificar); // MODIFICAR
                    StaticCode.Alerts("INFORMATION", "Modificar Coche", "INFORMATION",
                            "Se ha modificado los datos del coche correctamente.");
                    refreshTable(); // ACTUALIZAR LA TABLA
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
    } // METODO PARA MODIFICAR LOS DATOS D EUN COCHE

    @FXML
    void onNuevoCocheAction(ActionEvent event) {
        // COMPROBAR CAMPOS VACIOS, SI HAY ALGUN CAMPO VACIO SALTA UN ERROR
        if (StaticCode.camposVacios(tipoComboBox, marcaTxt, matriculaTxt, modeloTxt)) {
            // COMPROBAR SI LA MATRICULA QUE SE VA A MODIFICAR YA ESTA EN USO
            if (!CocheDAO.estaMatricula(matriculaTxt.getText(), null)) {
                Coche coche = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
                CocheDAO.insertCar(coche); // INSERTAR
                onCancelarAction();
                refreshTable(); // ACTUALIZA LA TABLA
            } else {
                StaticCode.Alerts("ERROR", "Error al modificar", "¡ERROR!",
                        "Ya existe esa matricula.");
            } // matricula en uso
        } else {
            StaticCode.Alerts("ERROR", "Campo vacio", "¡ERROR!",
                    "Por favor, rellene todos los datos del formulario.");
        }
    } // CREAR UN COCHE NUEVO

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = ConnectionDB.conectar(); // CONECTAR A LA BD MONGODB
        MongoDatabase database = con.getDatabase("Coches"); // OBTENER LA BASE DE DATOS DESDE LA CONEXION
        collection = database.getCollection("Coches"); // OBTENER LA COLECCION LLAMADA 'Coches' DE LA DB

        // INICIALIZAR LOS COMBOBOX
        tipoComboBox.getItems().addAll(tipoCoches); // AÑADIR LOS VALORES AL COMBOBOX
        refreshTable(); // CARGAR LOS DATOS EN LA TABLA AL INICIAR
    }

    public void refreshTable() {
        // PASAR LOS DATOS A LA TABLA CUANDO SE INICIE EL PROGRAMA
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        cochesTable.setItems(CocheDAO.listCar()); // ESTABLECER LISTA
    } // ACTUALIZA LOS DATOS ACTUALES DE LA BASE DE DATOS
}