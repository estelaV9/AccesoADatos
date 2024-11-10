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
import java.sql.SQLException;
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
    void onLimpiarAction(){
        idMultaTF.clear();
        datePicker.setValue(null);
        precioTF.clear();
    } // BOTON PARA LIMPIAR LOS CAMPOS

    @FXML
    void onActualizarAction(ActionEvent event) {
        Multa fineSelected = multaTable.getSelectionModel().getSelectedItem(); // OBJETO CON LA MULTA SELECCIONADA
        if (fineSelected == null) {
            StaticCode.Alerts("ERROR", "Selecciona una multa", "¡ERROR!",
                    "Por favor, seleccione una multa para eliminar");
        } else {
            Multa fine = new Multa(Integer.parseInt(idMultaTF.getText()), Double.parseDouble(precioTF.getText()), datePicker.getValue().toString(), matriculaTF.getText());
            if(multaDAO.modifyFine(fine, fineSelected.getIdMulta())){
                // SI SE MODIFICO LA MULTA CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Modificación de multa",
                        "Modificación exitosa","Se ha modificado la multa correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE MODIFICO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Modificación de multa",
                        "Modificación fallida","No se ha podido modificar la multa.");
            } // MODIFICAR LA MULTA
        } // VALIDAR SI HA SELECCIONADO UNA MULTA
    } // BOTON PARA MODIFICAR UNA MULTA DE UNA MATRICULA SELECCIONADA

    @FXML
    void onBackAction(ActionEvent event) {
        StaticCode.changeViewBtt("/ui/Coche.fxml", backBtt, "Gestión Coches y Multas");
    } // BOTON QUE CAMBIA DE ESCENA A LA VISTA COCHE

    @FXML
    void onBorrarAction(ActionEvent event) {
        Multa fineSelected = multaTable.getSelectionModel().getSelectedItem(); // OBJETO CON LA MULTA SELECCIONADA
        if (fineSelected == null) {
            StaticCode.Alerts("ERROR", "Selecciona una multa", "¡ERROR!",
                    "Por favor, seleccione una multa para eliminar");
        } else {
            if(multaDAO.deleteFine(fineSelected.getMatricula())){
                // SI SE ELIMINO LA MULTA CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Eliminación de multa",
                        "Eliminación exitosa","Se ha eliminado la multa correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE ELIMINO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Eliminación de multa",
                        "Eliminación fallida","No se ha podido eliminar la multa.");
            } // BORRAR LA MULTA
        } // VALIDAR SI HA SELECCIONADO UNA MULTA
    } // BOTON PARA ELIMINAR UNA MULTA DE UNA MATRICULA SELECCIONADA

    @FXML
    void onClickedTable(MouseEvent event) {
        Multa fineSelected = multaTable.getSelectionModel().getSelectedItem(); // OBJETO CON LA MULTA SELECCIONADA
        if(fineSelected != null){
            idMultaTF.setText(String.valueOf(fineSelected.getIdMulta()));
            precioTF.setText(String.valueOf(fineSelected.getPrecio()));
            datePicker.setValue(LocalDate.parse(fineSelected.getFecha()));
        } // SI SE HA SELECCIONADO UNA MULTA SE SETTEAN SUS DATOS
    } // CUANDO SE PULSA UNA MULTA SE ASIGNAN LOS VALORES DE LA MULTA SELECCIONADA

    @FXML
    void onExitAction(ActionEvent event) throws SQLException {
        StaticCode.exitApp();
    } // BOTON PARA SALIR DE LA APLICACION

    @FXML
    void onInsertarAction(ActionEvent event) {
        Multa fine = new Multa(Integer.parseInt(idMultaTF.getText()), Double.parseDouble(precioTF.getText()), datePicker.getValue().toString(), matriculaTF.getText());
        if(multaDAO.insertFine(fine)){
            // SI SE INSERTO LA MULTA CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
            StaticCode.Alerts("INFORMATION", "Creación de multa", "Creación exitosa",
                    "Se ha creado la multa correctamente.");
            refreshTable(); // REFRESCAR LOS DATOS
        } else {
            // SI NO SE INSERTO, SE MUESTRA UN ERROR
            StaticCode.Alerts("ERROR", "Creación de multa", "Creación fallida",
                    "No se ha podido crear la multa.");
        } // INSERTAR COCHE
    } // BOTON PARA INSERTAR UNA MULTA A UN COCHE

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