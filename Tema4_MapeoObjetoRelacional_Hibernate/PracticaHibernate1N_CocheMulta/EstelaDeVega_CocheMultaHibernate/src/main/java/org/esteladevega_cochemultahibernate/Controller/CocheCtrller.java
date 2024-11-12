package org.esteladevega_cochemultahibernate.Controller;

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
import org.esteladevega_cochemultahibernate.DAO.CocheDAO;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.esteladevega_cochemultahibernate.Utilities.StaticCode;

import java.net.URL;
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

    String[] listaTipos = {"Familiar", "Monovolumen", "Deportivo", "SUV"};
    CocheDAO cocheDAO = new CocheDAO();


    @FXML
    void onCancelarAction() {
        matriculaTxt.clear();
        marcaTxt.clear();
        modeloTxt.clear();
        tipoComboBox.setValue(null);
    } // BOTON PARA BORRAR LOS CAMPOS

    @FXML
    void onClickedTable(MouseEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        if (seleccionada != null) {
            matriculaTxt.setText(seleccionada.getMatricula());
            marcaTxt.setText(seleccionada.getMarca());
            modeloTxt.setText(seleccionada.getModelo());
            tipoComboBox.setValue(seleccionada.getTipo());
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // CUANDO PULSA UN COCHE, SE SETTEAN LOS VALORES

    @FXML
    void onEliminarAction(ActionEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        // COMPROBAR SI EL USUARIO HA SELECCIONADO UN COCHE PARA MODIFICAR
        if (seleccionada != null) {
            cocheDAO.eliminarCoche(seleccionada); // SE ELIMINA EL COCHE
            StaticCode.Alerts("INFORMATION", "Eliminar Coche", "INFORMATION",
                    "Se ha eliminado los datos del coche correctamente.");
            onCancelarAction();
            refreshTable(); // ACTUALIZAR LA TABLA
        } else {
            StaticCode.Alerts("ERROR", "Coche vacio", "¡ERROR!",
                    "Por favor, seleccione un coche para eliminar.");
        } // coche vacio
    } // ELIMINAR UN COCHE

    @FXML
    void onExitAction(ActionEvent event) throws SQLException {
        StaticCode.exitApp(cocheDAO.session);
    } // BOTON PARA SALIR DE LA APP CERRANDO LA SESION

    @FXML
    void onModificarAction(ActionEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        // COMPROBAR SI EL USUARIO HA SELECCIONADO UN COCHE PARA MODIFICAR
        if (seleccionada != null) {
            // COMPROBAR SI NO HAY CAMPOS VACIOS
            if (StaticCode.camposVacios(tipoComboBox, matriculaTxt, marcaTxt, modeloTxt)) {
                Coche cocheModificar = new Coche(seleccionada.getIdCoche(), seleccionada.getMatricula(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
                cocheDAO.modificarCoche(cocheModificar); // MODIFICAR
                StaticCode.Alerts("INFORMATION", "Modificar Coche", "INFORMATION",
                        "Se ha modificado los datos del coche correctamente.");
                onCancelarAction();
                refreshTable(); // ACTUALIZAR LA TABLA
            } else {
                StaticCode.Alerts("ERROR", "Campo vacio", "¡ERROR!",
                        "Por favor, rellene todos los datos del formulario.");
            } // campos vacios
        } else {
            StaticCode.Alerts("ERROR", "Coche vacio", "¡ERROR!",
                    "Por favor, seleccione un coche para eliminar.");
        } // coche vacio
    } // METODO PARA MODIFICAR LOS DATOS DE UN COCHE

    @FXML
    void onNuevoCocheAction(ActionEvent event) {
        // COMPROBAR CAMPOS VACIOS, SI HAY ALGUN CAMPO VACIO SALTA UN ERROR
        if (StaticCode.camposVacios(tipoComboBox, marcaTxt, matriculaTxt, modeloTxt)) {
            Coche coche = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
            cocheDAO.insertarCoche(coche); // INSERTAR
            onCancelarAction();
            refreshTable(); // ACTUALIZA LA TABLA
        } else {
            StaticCode.Alerts("ERROR", "Campo vacio", "¡ERROR!",
                    "Por favor, rellene todos los datos del formulario.");
        }
    } // CREAR UN COCHE NUEVO

    @FXML
    void onVerMultasAction(ActionEvent event) {
        Coche cocheSeleccionado = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        if (cocheSeleccionado != null) {
            StaticCode.cambiarVistaBtt("/ui/Multa.fxml", verMultasBtt, cocheSeleccionado, "Multa");
        } else {
            StaticCode.Alerts("ERROR", "Coche vacio",
                    "Seleccion nula", "Para ir al formulario de multas, seleccione un coche.");
        } // SI SELECCIONADO NO ES NULO, SE VA AL FORMULARIO JUGADORES
    } // BOTON PARA IR A LA VISTA JUGADORES

    private void refreshTable() {
        cochesTable.setItems(cocheDAO.listarCoches());
    } // METODO PARA AÑADIR LA LSITA DE COCHES A LA TABLA


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // INICIALIZAR LOS COMBOBOX
        tipoComboBox.getItems().addAll(listaTipos); // AÑADIR LOS VALORES AL COMBOBOX

        // CONFIGURAR COLUMNAS
        idCol.setCellValueFactory(new PropertyValueFactory<>("idCoche"));
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marca"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        refreshTable(); // INSERTAR LOS DATOS EN LA TABLA
    }
}