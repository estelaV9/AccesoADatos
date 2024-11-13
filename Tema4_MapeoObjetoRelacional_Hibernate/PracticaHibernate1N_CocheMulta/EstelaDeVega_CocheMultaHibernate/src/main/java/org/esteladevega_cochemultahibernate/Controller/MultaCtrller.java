package org.esteladevega_cochemultahibernate.Controller;

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
import org.esteladevega_cochemultahibernate.DAO.InterfaceCoche;
import org.esteladevega_cochemultahibernate.DAO.MultaDAO;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.esteladevega_cochemultahibernate.Model.Multa;
import org.esteladevega_cochemultahibernate.Utilities.StaticCode;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MultaCtrller implements Initializable {

    @FXML
    private Button actualizarBtt;

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
    Coche cocheGeneral = new Coche();

    public void displayController(Coche coche) {
        matriculaTF.setText(coche.getMatricula()); // SE SETTEA LA MATRICULA DE COCHE
        cocheGeneral = coche; // SE SETTEA PARA PODER ACCEDER A ESE ATRIBUTO
        refreshTable(); // SE REFRESCA LA TABLA
    } // METODO PARA PASAR LA MATRICULA DEL COCHE SELECCIONADO

    @FXML
    void onLimpiarAction() {
        idMultaTF.clear();
        precioTF.clear();
        datePicker.setValue(null);
    } // BOTON PARA LIMPIAR LOS CAMPOS

    @FXML
    void onActualizarAction(ActionEvent event) {
        Multa multaSeleccionada = multaTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DE A¡LA MULTA SELECCIONADO
        if (multaSeleccionada != null) {
            if (precioTF.getText().isEmpty() || datePicker.getValue() == null) {
                Multa multa = new Multa(multaSeleccionada.getIdMulta(), Double.parseDouble(precioTF.getText()),
                        datePicker.getValue(), matriculaTF.getText());
                if (multaDAO.modificarMulta(multa)) {
                    StaticCode.Alerts("INFORMATION", "Modificar Multa",
                            "Modificar Multa", "Se ha modificado correctamente la multa");
                    refreshTable(); // ACTUALIZA LA TABLA
                    onLimpiarAction(); // LIMPIAR LOS CAMPOS
                } else {
                    StaticCode.Alerts("ERROR", "Modificar Multa",
                            "Fallo al modificar", "NO se ha modificado correctamente la multa");
                }// INSERTAR MULTA
            } else {
                StaticCode.Alerts("ERROR", "Campos vacios", "¡ERROR!",
                        "Por favor, rellene todos los datos.");
            } // CAMPOS VACIOS
        } else {
            StaticCode.Alerts("ERROR", "Multa vacio", "¡ERROR!",
                    "Por favor, seleccione una multa para eliminar.");
        } // MULTA VACIA
    } // BOTON PARA ACTUALIZAR MULTA

    @FXML
    void onBorrarAction(ActionEvent event) {
        Multa multaSeleccionada = multaTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DE LA MULTA SELECCIONADA
        if (multaSeleccionada != null) {
            if (precioTF.getText().isEmpty() || datePicker.getValue() == null) {
                multaDAO.eliminarMulta(multaSeleccionada); // SE ELIMINA LA MULTA
                StaticCode.Alerts("INFORMATION", "Eliminar Multa", "INFORMATION",
                        "Se ha eliminado los datos de la multa correctamente.");
                refreshTable(); // ACTUALIZAR LA TABLA
                onLimpiarAction(); // LIMPIAR LOS CAMPOS
            } else {
                StaticCode.Alerts("ERROR", "Campos vacios", "¡ERROR!",
                        "Por favor, rellene todos los datos.");
            } // CAMPOS VACIOS
        } else {
            StaticCode.Alerts("ERROR", "Multa vacia", "¡ERROR!",
                    "Por favor, seleccione una multa para eliminar.");
        } // multa vacia
    } // BOTON PARA ELIMINAR LA MULTA

    @FXML
    void onClickedTable(MouseEvent event) {
        Multa multaSeleccionada = multaTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DE A¡LA MULTA SELECCIONADO
        if (multaSeleccionada != null) {
            idMultaTF.setText(String.valueOf(multaSeleccionada.getIdMulta()));
            precioTF.setText(String.valueOf(multaSeleccionada.getPrecio()));
            datePicker.setValue(multaSeleccionada.getFecha());
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // CUANDO PULSA UNA MULTA, SE SETTEAN LOS VALORES

    @FXML
    void onExitAction(ActionEvent event) throws SQLException {
        StaticCode.exitApp(multaDAO.session);
    } // BOTON PARA SALIR DE LA APP CERRANDO LA SESION

    @FXML
    void onInsertarAction(ActionEvent event) {
        if (!precioTF.getText().isEmpty() && datePicker.getValue() != null) {
            Multa multa = new Multa(1, Double.parseDouble(precioTF.getText()), datePicker.getValue(), matriculaTF.getText());
            if (multaDAO.insertarMulta(multa)) {
                StaticCode.Alerts("INFORMATION", "Inserción Multa",
                        "Insertar Multa", "Se ha insertado correctamente la multa");
                refreshTable(); // ACTUALIZA LA TABLA
                onLimpiarAction(); // LIMPIAR LOS CAMPOS
            } else {
                StaticCode.Alerts("ERROR", "Inserción Multa",
                        "Fallo al insertar", "NO se ha insertado correctamente la multa");
            }// INSERTAR MULTA
        } else {
            StaticCode.Alerts("ERROR", "Campos vacios", "¡ERROR!",
                    "Por favor, rellene todos los datos.");
            System.out.println(precioTF.getText() + " " + datePicker.getValue());
        } // CAMPOS VACIOS
    } // CREAR UN COCHE NUEVO

    private void refreshTable() {
        multaTable.setItems(multaDAO.listarMultas(cocheGeneral.getMatricula()));
    } // METODO PARA AÑADIR LA LSITA DE COCHES A LA TABLA

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idMultaTF.setDisable(true); //  EL ID DE MULTA NO SE PODRA MODIFICAR
        matriculaTF.setDisable(true); // EL MATRICULA NO SE PODRA MODIFICAR
        // CONFIGURAR COLUMNAS
        idCol.setCellValueFactory(new PropertyValueFactory<>("idMulta"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        refreshTable(); // INSERTAR LOS DATOS A LA TABLA
    }
}