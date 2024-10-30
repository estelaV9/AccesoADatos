package org.esteladevega_crudcocheshibernate.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.esteladevega_crudcocheshibernate.DAO.CocheDAO;
import org.esteladevega_crudcocheshibernate.Model.Coche;
import org.esteladevega_crudcocheshibernate.StaticCode.StaticCode;
import org.esteladevega_crudcocheshibernate.Utilities.HibernateUtil;
import org.hibernate.Session;

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
    private TableColumn<Coche, Integer> idCol;
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

    String[] tipoCoches = {"Diesel", "Gasolina", "Electrico"}; // OPCIONES COMBOBOX
    // SessionFactory factory = HibernateUtil.getSessionFactory(); // CREARA OBJETOS DE TIPO SE¡ession
    Session session = HibernateUtil.getSession();
    CocheDAO cocheDAO = new CocheDAO();


    @FXML
    void onExitAction() {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
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
            cocheDAO.eliminarCoche(session, seleccionada); // SE ELIMINA EL COCHE
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
                Coche cocheModificar = new Coche(seleccionada.getCocheID(), seleccionada.getMatriculaCoche(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());
                cocheDAO.modificarCoche(session, cocheModificar); // MODIFICAR
                StaticCode.Alerts("INFORMATION", "Modificar Coche", "INFORMATION",
                        "Se ha modificado los datos del coche correctamente.");
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
            cocheDAO.insertarCoche(session, coche); // INSERTAR
            onCancelarAction();
            refreshTable(); // ACTUALIZA LA TABLA
        } else {
            StaticCode.Alerts("ERROR", "Campo vacio", "¡ERROR!",
                    "Por favor, rellene todos los datos del formulario.");
        }
    } // CREAR UN COCHE NUEVO

    @FXML
    void onClickedTable(MouseEvent event) {
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL COCHE SELECCIONADO
        if (seleccionada != null) {
            matriculaTxt.setText(seleccionada.getMatriculaCoche());
            marcaTxt.setText(seleccionada.getMarcaCoche());
            modeloTxt.setText(seleccionada.getModeloCoche());
            tipoComboBox.setValue(seleccionada.getTipoCoche());
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // CUANDO PULSA UN COCHE, SE SETTEAN LOS VALORES

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // PASAR LOS DATOS A LA TABLA CUANDO SE INICIE EL PROGRAMA
        idCol.setCellValueFactory(new PropertyValueFactory<>("cocheID"));
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marcaCoche"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipoCoche"));
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matriculaCoche"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modeloCoche"));

        // INICIALIZAR LOS COMBOBOX
        tipoComboBox.getItems().addAll(tipoCoches); // AÑADIR LOS VALORES AL COMBOBOX
        refreshTable(); // CARGAR LOS DATOS EN LA TABLA AL INICIAR
    }

    public void refreshTable() {
        cochesTable.setItems(cocheDAO.listarCoches(session)); // ESTABLECER LISTA
    } // ACTUALIZA LOS DATOS ACTUALES DE LA BASE DE DATOS
}