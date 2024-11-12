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
    void onCancelarAction(ActionEvent event) {
        matriculaTxt.clear();
        marcaTxt.clear();
        modeloTxt.clear();
        tipoComboBox.setValue(null);
    } // BOTON PARA BORRAR LOS CAMPOS

    @FXML
    void onClickedTable(MouseEvent event) {

    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @FXML
    void onExitAction(ActionEvent event) throws SQLException {
        StaticCode.exitApp(cocheDAO.session);
    } // BOTON PARA SALIR DE LA APP CERRANDO LA SESION

    @FXML
    void onModificarAction(ActionEvent event) {

    }

    @FXML
    void onNuevoCocheAction(ActionEvent event) {

    }

    @FXML
    void onVerMultasAction(ActionEvent event) {

    }

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