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
import javafx.scene.control.*;
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


    @FXML
    private ToggleGroup dbGroup;
    @FXML
    private RadioButton hibernateRB;
    @FXML
    private RadioButton mongoRB;
    @FXML
    private RadioButton mySQLRB;


    String[] tipoCoche = {"Eléctrico", "Diesel", "Híbrido"}; // ARRAY CON LOS TIPOS DE COCHES
    CocheDAO cocheDAO = new CocheDAO(); // INSTANCIAR CocheDAO


    private boolean executeAction(String action, Coche coche, String matricula) {
        // OBTENER GESTOR SELECCIONADO
        String selectedGestor = ((RadioButton) dbGroup.getSelectedToggle()).getText();

        // RESULTADO POR DEFECTO (INICIALIZADO EN FALSO)
        boolean result = false;

        // EVALUAR EL GESTOR SELECCIONADO PARA EJECUTAR LA ACCIÓN CORRESPONDIENTE
        switch (selectedGestor) {
            case "MySQL" -> {
                // SI LA ACCIÓN ES ELIMINAR, SE EJECUTA LA FUNCIÓN DE ELIMINACIÓN EN MYSQL
                if ("eliminar".equals(action)) {
                    result = cocheDAO.deleteCar(coche.getMatricula()); // ELIMINAR
                }
                // SI LA ACCIÓN ES MODIFICAR, SE EJECUTA LA LÓGICA DE MODIFICACIÓN EN MYSQL
                else if ("modificar".equals(action)) {
                    result = cocheDAO.modifyCar(coche, matricula); // MODIFICAR
                }
                // SI LA ACCIÓN ES INSERTAR, SE EJECUTA LA LÓGICA DE INSERCIÓN EN MYSQL
                else if ("insertar".equals(action)) {
                    result = cocheDAO.insertCar(coche); // INSERTAR
                }
            }
            /*case "MongoDB" -> {
                // SI LA ACCIÓN ES ELIMINAR, SE EJECUTA LA LÓGICA DE ELIMINACIÓN EN MONGODB
                if ("eliminar".equals(action)) {
                    // ELIMINAR
                }
                // SI LA ACCIÓN ES MODIFICAR, SE EJECUTA LA LÓGICA DE MODIFICACIÓN EN MONGODB
                else if ("modificar".equals(action)) {
                    // MOFICIAR
                }
                // SI LA ACCIÓN ES INSERTAR, SE EJECUTA LA LÓGICA DE INSERCIÓN EN MONGODB
                else if ("insertar".equals(action)) {
                    // INSERTAR
                }
            }
            case "Hibernate" -> {
                // SI LA ACCIÓN ES ELIMINAR, SE EJECUTA LA LÓGICA DE ELIMINACIÓN EN HIBERNATE
                if ("eliminar".equals(action)) {
                    // ELIMINAR
                }
                // SI LA ACCIÓN ES MODIFICAR, SE EJECUTA LA LÓGICA DE MODIFICACIÓN EN HIBERNATE
                else if ("modificar".equals(action)) {
                    // MODIFICAR
                }
                // SI LA ACCIÓN ES INSERTAR, SE EJECUTA LA LÓGICA DE INSERCIÓN EN HIBERNATE
                else if ("insertar".equals(action)) {
                    // INSERTAR
                }
            }*/
            default ->
                // SI NO SE SELECCIONA UN GESTOR VÁLIDO, SE MUESTRA UN MENSAJE DE ERROR
                    StaticCode.Alerts("ERROR", "Gestor desconocido", "¡ERROR!", "No se ha seleccionado un gestor válido.");
        }

        // DEVOLVER EL RESULTADO DE LA ACCIÓN (TRUE SI SE EJECUTÓ CORRECTAMENTE, FALSE EN CASO CONTRARIO)
        return result;
    }


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
            // GUARDA EL RESULTADO DE LLAMAR A LA FUNCION PARA ELIMINAR EL COCHE
            boolean result = executeAction("eliminar", carSelected, null); // ATRIBUTO PARA SABER SI LA ELIMINACION HA SIDO EXITOSA O NO

            // MOSTRAR SI SE ELIMINO CORRECTAMENTE O NO
            if (result) {
                // SI SE ELIMINO EL COCHE CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Eliminación de coche",
                        "Eliminación exitosa", "Se ha eliminado el coche correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE ELIMINO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Eliminación de coche",
                        "Eliminación fallida", "No se ha podido eliminar el coche.");

            }
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

            // GUARDA EL RESULTADO DE LLAMAR A LA FUNCION PARA ELIMINAR EL COCHE
            boolean result = executeAction("modificar", newCar, carSelected.getMatricula());

            // MOSTRAR SI SE ELIMINO CORRECTAMENTE O NO
            if (result) {
                // SI SE MODIFICO EL COCHE CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Modificación de coche",
                        "Modificación exitosa", "Se ha modificado el coche correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE MODIFICO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Modificación de coche",
                        "Modificación fallida", "No se ha podido modificar el coche.");
            } // MODIFICAR EL COCHE
        } // VALIDAR SI HA SELECCIONADO UN COCHE
    } // BOTON PARA MODIFICAR LOS DATOS DE UN COCHE SELECCIONADO

    @FXML
    void onNuevoCocheAction(ActionEvent event) {
        Coche insertCarObject = new Coche(matriculaTxt.getText(), marcaTxt.getText(), modeloTxt.getText(), tipoComboBox.getValue());

        // GUARDA EL RESULTADO DE LLAMAR A LA FUNCION PARA INSERTAR EL COCHE
        boolean result = executeAction("insertar", insertCarObject, null);

        if (result) {
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
        Coche seleccionada = cochesTable.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            // CAMBIAR DE VISTA GUARDANDO LOS DATOS DEL COCHE SELECCIONADO
            StaticCode.changeViewWithPharamsBtt("/ui/Multa.fxml", verMultasBtt, "Multas", seleccionada);
        } else {
            StaticCode.Alerts("ERROR", "Selecciona un coche", "¡ERROR!",
                    "Por favor, seleccione un coche");
        } // SI SE SELECCIONA VA A LA VISTA MULTAS, Y SI NO LANZA UN MENSAJE
    } // CAMBIAR DE VISTA

    private void refreshTable() {
        cochesTable.setItems(cocheDAO.listOfCars()); // ESTABLECER LISTA
    } // METODO PARA ESTABLECER LOS DATOS EN LA TABLA

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = MySQL_ConnectionDB.conectar();
        tipoComboBox.getItems().addAll(tipoCoche); // INICIALIZAR LOS DATOS DEL COMBOBOX

        // CONFIGURAR COLUMNAS
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        refreshTable(); // AÑADIR LOS DATOS A LA TABLA

        mySQLRB.setSelected(true); // POR DEFECTO APARECE SELECCIONADA LA OPCION DE MYSQL
    } // INICIARLIZAR LOS DATOS DEL COMBOBOX Y LA TABLA
}