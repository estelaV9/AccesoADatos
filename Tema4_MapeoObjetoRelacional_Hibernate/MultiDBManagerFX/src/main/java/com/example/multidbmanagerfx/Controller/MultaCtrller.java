package com.example.multidbmanagerfx.Controller;

import com.example.multidbmanagerfx.DAO.MultaDAO;
import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Model.Multa;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MultaCtrller implements Initializable {
    @FXML
    private Button actualizarBtt;
    @FXML
    private Button backBtt;
    @FXML
    private Button borrarBtt;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button exitBtt;
    @FXML
    private TableColumn<LocalDate, Multa> fechaCol;
    @FXML
    private TableColumn<Integer, Multa> idCol;
    @FXML
    private TextField idMultaTF;
    @FXML
    private Button insertarBtt;
    @FXML
    private Button limpiarBtt;
    @FXML
    private TextField matriculaTF;
    @FXML
    private TableView<Multa> multaTable;
    @FXML
    private TableColumn<Double, Multa> precioCol;
    @FXML
    private TextField precioTF;

    MultaDAO multaDAO = new MultaDAO();

    String numberPlate; // VARIABLE PARA GUARDAR LA MATRICULA DEL COCHE SELECCIONADO

    public void displayCoche(Coche coche) {
        matriculaTF.setText(coche.getMatricula()); // SETTEA LA MATRICULA DEL COCHE SELECI
        matriculaTF.setEditable(false);
        numberPlate = coche.getMatricula(); // ASIGNA LA MATRICULA A LA VARIABLE GLOBAL
        refreshTable(); // LLAMA A refreshTable() DESPUES DE HABER ASIGNADO LA MATRICULA
    } // METODO QUE GUARDA LOS DATOS DEL COCHE SELECCIONADO CUANDO SE CAMBIA DE ESCENA


    @FXML
    void onActualizarAction(ActionEvent event) {

    }

    @FXML
    void onBackAction(ActionEvent event) {
        StaticCode.changeViewBtt("/ui/Coche.fxml", backBtt, "Gestión Coches y Multas");
    } // BOTON QUE CAMBIA DE ESCENA A LA VISTA COCHE

    @FXML
    void onBorrarAction(ActionEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {
        Multa fineSelected = multaTable.getSelectionModel().getSelectedItem(); // OBJETO CON LA MULTA SELECCIONADA
        if(fineSelected != null){
            idMultaTF.setText(String.valueOf(fineSelected.getIdMulta()));
            precioTF.setText(String.valueOf(fineSelected.getPrecio()));
            datePicker.setValue(fineSelected.getFecha());
        } // SI SE HA SELECCIONADO UNA MULTA SE SETTEAN SUS DATOS
    } // CUANDO SE PULSA UNA MULTA SE ASIGNAN LOS VALORES DE LA MULTA SELECCIONADA

    @FXML
    void onExitAction(ActionEvent event) {

    }

    @FXML
    void onInsertarAction(ActionEvent event) {

    }

    private void refreshTable() {
        // CONFIGURAR COLUMNAS
        idCol.setCellValueFactory(new PropertyValueFactory<>("idMulta"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        multaTable.setItems(multaDAO.listOfFines(numberPlate)); // ESTABLECER LISTA
    } // METODO PARA ESTABLECER LOS DATOS EN LA TABLA Y SETTEAR LA MATRICULA

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
    }
}