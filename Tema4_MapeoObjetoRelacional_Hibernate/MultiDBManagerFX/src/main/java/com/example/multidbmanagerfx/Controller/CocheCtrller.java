package com.example.multidbmanagerfx.Controller;

import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import com.example.multidbmanagerfx.DAO.CocheDAO;
import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Integer, Coche> idCol;
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

    String[] tipoCoche = {"Eléctrico", "Diesel", "Híbrido"}; // ARRAY CON LOS TIPOS DE COCHES
    CocheDAO cocheDAO = new CocheDAO(); // INSTANCIAR CocheDAO

    @FXML
    void onCancelarAction(ActionEvent event) {
        matriculaTxt.clear();
        modeloTxt.clear();
        tipoComboBox.setValue(null);
        marcaTxt.clear();
    } // METODO PARA LIMPIAR LOS CAMPOS

    @FXML
    void onClickedTable() {
        Coche carSelected = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        if (carSelected != null) {
            matriculaTxt.setText(carSelected.getMatricula());
            marcaTxt.setText(carSelected.getMarca());
            modeloTxt.setText(carSelected.getModelo());
            tipoComboBox.setValue(carSelected.getTipo());
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // CUANDO PULSA UN COCHE, SE SETTEAN LOS VALORES


    @FXML
    void onEliminarAction(ActionEvent event) {
        Coche carSelected = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        if (carSelected == null) {
            StaticCode.Alerts("ERROR", "Selecciona un coche", "¡ERROR!",
                    "Por favor, seleccione un coche");
        } else {
            if(cocheDAO.deleteCar(carSelected.getMatricula())){
                // SI SE ELIMINO EL COCHE CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Eliminación de coche",
                        "Eliminación exitosa","Se ha eliminado el coche correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE ELIMINO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Eliminación de coche",
                        "Eliminación fallida","No se ha podido eliminar el coche.");
            } // MODIFICAR EL COCHE
        } // VALIDAR SI HA SELECCIONADO UN COCHE
    } // BOTON PARA ELIMINAR UN COCHE DE UNA MATRICULA SELECCIONADA

    @FXML
    void onExitAction(ActionEvent event) throws SQLException {
        StaticCode.exitApp();
    }

    @FXML
    void onModificarAction(ActionEvent event) {
        Coche carSelected = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        if (carSelected == null) {
            StaticCode.Alerts("ERROR", "Selecciona un coche", "¡ERROR!",
                    "Por favor, seleccione un coche");
        } else {
            // OBJETO CON LOS NUEVOS DATOS DEL COCHE
            Coche newCar = new Coche(marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
            if(cocheDAO.modifyCar(newCar, carSelected.getMatricula())){
                // SI SE MODIFICO EL COCHE CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Modificación de coche",
                        "Modificación exitosa","Se ha modificado el coche correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE MODIFICO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Modificación de coche",
                        "Modificación fallida","No se ha podido modificar el coche.");
            } // MODIFICAR EL COCHE
        } // VALIDAR SI HA SELECCIONADO UN COCHE
    } // BOTON PARA MODIFICAR LOS DATOS DE UN COCHE SELECCIONADO

    @FXML
    void onNuevoCocheAction(ActionEvent event) {
        Coche insertCarObject = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
        if (cocheDAO.insertCar(insertCarObject)) {
            // SI SE INSERTO EL COCHE CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
            StaticCode.Alerts("INFORMATION", "Creación de coche", "Creación exitosa",
                    "Se ha creado el coche correctamente.");
            refreshTable(); // REFRESCAR LOS DATOS
        } else {
            // SI NO SE INSERTO, SE MUESTRA UN ERROR
            StaticCode.Alerts("ERROR", "Creación de coche", "Creación fallida",
                    "No se ha podido crear el coche.");
        } // INSERTAR COCHE
    } // BOTON PARA AGREGAR UN COCHE NUEVO

    @FXML
    void onVerMultasAction(ActionEvent event) {

    }

    private void refreshTable() {
        // CONFIGURAR COLUMNAS
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        cochesTable.setItems(cocheDAO.listOfCars()); // ESTABLECER LISTA
    } // METODO PARA ESTABLECER LOS DATOS EN LA TABLA

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = MySQL_ConnectionDB.conectar();
        tipoComboBox.getItems().addAll(tipoCoche); // INICIALIZAR LOS DATOS DEL COMBOBOX
        refreshTable(); // AÑADIR LOS DATOS A LA TABLA
    } // INICIARLIZAR LOS DATOS DEL COMBOBOX Y LA TABLA
}