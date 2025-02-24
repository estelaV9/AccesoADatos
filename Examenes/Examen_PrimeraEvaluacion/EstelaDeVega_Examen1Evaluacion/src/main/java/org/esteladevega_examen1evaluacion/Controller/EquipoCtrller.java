package org.esteladevega_examen1evaluacion.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.esteladevega_examen1evaluacion.DAO.Hibernate_EquipoDAO;
import org.esteladevega_examen1evaluacion.DAO.MongoDB_EquipoDAO;
import org.esteladevega_examen1evaluacion.DAO.MySQL_EquipoDAO;
import org.esteladevega_examen1evaluacion.Model.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EquipoCtrller implements Initializable {
    @FXML
    private Button altaEquipoBtt;
    @FXML
    private TableColumn<String, Equipo> categoriaCol;
    @FXML
    private ComboBox<String> categoriaCombobox;
    @FXML
    private Button eliminarEquipoBtt;
    @FXML
    private TableView<Equipo> equiposTable;
    @FXML
    private Button formularioJugadoresBtt;
    @FXML
    private TableColumn<Integer, Equipo> idEquipoCol;
    @FXML
    private TextField idEquipoTF;
    @FXML
    private TableColumn<String, Equipo> nombreEquipoCol;
    @FXML
    private Button limpiarBtt;
    @FXML
    private Button modificarEstadoBtt;
    @FXML
    private RadioButton noRD;
    @FXML
    private TextField nombreEquipoTF;
    @FXML
    private TableColumn<String, Equipo> patrocinadorCol;
    @FXML
    private TextField patrocinadorTF;
    @FXML
    private TableColumn<Boolean, Equipo> sancionadoCol;
    @FXML
    private ToggleGroup sancionadoGroup;
    @FXML
    private RadioButton siRB;

    Hibernate_EquipoDAO hibernateEquipoDAO = new Hibernate_EquipoDAO();
    MySQL_EquipoDAO mySQLEquipoDAO = new MySQL_EquipoDAO();
    MongoDB_EquipoDAO mongoDBEquipoDAO = new MongoDB_EquipoDAO();


    /*3- 3.	(1.5 Puntos) Al pulsar el botón Alta Equipo, se deberán dar de Alta un equipo en MongoDB y en MySQL.
    Primero se pulsará el botón Limpiar Campos para posteriormente una vez introducidos pulsar el Botón Alta Equipo
    para insertarlo en los 2 SGBD.*/
    @FXML
    void onAltaEquipoAction(ActionEvent event) {
        String seleccionado = ((RadioButton) sancionadoGroup.getSelectedToggle()).getText();
        boolean sancionadoSelect, mysql, mongo;

        if (seleccionado.equals("Si")) {
            sancionadoSelect = true;
        } else {
            sancionadoSelect = false;
        } // DAR VALOR A SI ESTA SELECCIONADO EL RADIOBUTTON O NO

        Equipo equipo = new Equipo(Integer.parseInt(idEquipoTF.getText()), nombreEquipoTF.getText(), patrocinadorTF.getText(),
                categoriaCombobox.getValue(), sancionadoSelect); // CREAR OBJETO EQUIPO
        mysql = mySQLEquipoDAO.insertEquipo(equipo); // INSERTAR EQUIPO EN MYSQL
        mongo = mongoDBEquipoDAO.insertEquipo(equipo); // INSERTAR EQUIPO EN MONGODB

        if (mysql && mongo) {
            // SI SE INSERTO EL EQUIPO CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
            StaticCode.Alerts("INFORMATION", "Creación de equipo", "Creación exitosa",
                    "Se ha creado el equipo correctamente.");
            refreshTable(); // REFRESCAR LOS DATOS
        } else {
            // SI NO SE INSERTO, SE MUESTRA UN ERROR
            StaticCode.Alerts("ERROR", "Creación de equipo", "Creación fallida",
                    "No se ha podido crear el equipo.");
        } // INSERTAR EQUIPO
    } // BOTON PARA DAR DE ALTA UN EQUIPO EN MYSQL Y MONGODB

    @FXML
    void onClickTable(MouseEvent event) {
        Equipo equipoSeleccionado = equiposTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL EQUIPO SELECCIONADO
        if (equipoSeleccionado != null) {
            idEquipoTF.setText(String.valueOf(equipoSeleccionado.getIdEquipo()));
            nombreEquipoTF.setText(equipoSeleccionado.getNombreEquipo());
            patrocinadorTF.setText(equipoSeleccionado.getPatrocinado());
            categoriaCombobox.setValue(equipoSeleccionado.getCategoria());

            if (equipoSeleccionado.isSancionado()) {
                noRD.setSelected(false);
                siRB.setSelected(true);
            } else {
                noRD.setSelected(true);
                siRB.setSelected(false);
            } // SE SETTEA SI ESTA SELECCIONADO O NO
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // CUANDO PULSA UN EQUIPO, SE SETTEAN LOS VALORES

    @FXML
    void onEliminarEquipoAction(ActionEvent event) {
        Equipo equipoSeleccionado = equiposTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL EQUIPO SELECCIONADO
        if (equipoSeleccionado != null) {
            boolean mongo, hibernate; // ATRIBUTOS PARA GUARDAR EL ESTADO DE ELIMINACION
            mongo = mongoDBEquipoDAO.eliminarEquipo(equipoSeleccionado.getIdEquipo());
            hibernate = hibernateEquipoDAO.eliminarEquipo(equipoSeleccionado);

            // SI LOS DOS SE HA ELIMINADO, SE MUESTRA UN MENSAJE
            if (mongo && hibernate) {
                // SI SE ELIMINO EL EQUIPO CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Eliminación de equipo",
                        "Eliminación exitosa", "Se ha eliminado el equipo correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE ELIMINO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Eliminación de equipo",
                        "Eliminación fallida", "No se ha podido eliminar el equipo.");
            } // ELIMINAR EQUIPO
        } else {
            StaticCode.Alerts("ERROR", "Equipo vacio",
                    "Seleccion nula", "Para modificar, seleccione un equipo.");
        } // SI SELECCIONADO NO ES NULO, SE ELIMINA, SI NO, MUESTRA ERROR
    } // BOTON PARA ELIMINAR UN EQUIPO SELECIONADO

    @FXML
    void onFormularioJugagoresAction(ActionEvent event) {
        Equipo equipoSeleccionado = equiposTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL EQUIPO SELECCIONADO
        if (equipoSeleccionado != null) {
            StaticCode.changeViewBtt("Jugador.fxml", formularioJugadoresBtt, equipoSeleccionado, "Jugadores");
        } else {
            StaticCode.Alerts("ERROR", "Equipo vacio",
                    "Seleccion nula", "Para ir al formulario de jugadores, seleccione un equipo.");
        } // SI SELECCIONADO NO ES NULO, SE VA AL FORMULARIO JUGADORES
    } // BOTON PARA IR A LA VISTA JUGADORES

    @FXML
    void onLimpiarCamposAction(ActionEvent event) {
        idEquipoTF.clear();
        nombreEquipoTF.clear();
        categoriaCombobox.setValue(null);
        patrocinadorTF.clear();
        noRD.setSelected(false);
        siRB.setSelected(false);
    } // BOTON PARA LIMPIAR LOS CAMPOS


    /*4- 4.	(1 Puntos) Se puede modificar el estado de sanción de un equipo. Primero se seleccionar el estado
    al que se  quiere cambiar de equipo, una vez seleccionado el estado , se pulsara sobre el botón Modificar
    estado sancionado y se modificara el estado del equipo en la BD . Esta operación se hará utilizando Hibérnate.*/
    @FXML
    void onModificarEstadoSancionadoAction(ActionEvent event) {
        Equipo equipoSeleccionado = equiposTable.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL EQUIPO SELECCIONADO
        if (equipoSeleccionado != null) {
            String seleccionado = ((RadioButton) sancionadoGroup.getSelectedToggle()).getText(); // SE COGE EL VALOR DEL RADIOBUTTON
            boolean sancionadoSelect;
            if (seleccionado.equals("Si")) {
                sancionadoSelect = true;
            } else {
                sancionadoSelect = false;
            } // DAR VALOR A SI ESTA SELECCIONADO EL RADIOBUTTON O NO

            if (hibernateEquipoDAO.modificarEquipo(equipoSeleccionado, sancionadoSelect)) {
                // SI SE MODIFICO EL EQUIPO CORRECTAMENTE, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Modificación de equipo",
                        "Modificación exitosa", "Se ha modificado el equipo correctamente.");
                refreshTable(); // REFRESCAR LOS DATOS
            } else {
                // SI NO SE MODIFICO, SE MUESTRA UN ERROR
                StaticCode.Alerts("ERROR", "Modificación de equipo",
                        "Modificación fallida", "No se ha podido modificar el equipo.");
            } // MODIFICAR SANCIONADO DEL EQUIPO
        } else {
            StaticCode.Alerts("ERROR", "Equipo vacio",
                    "Seleccion nula", "Para modificar, seleccione un equipo.");
        } // SI SELECCIONADO NO ES NULO, SE MODIFICA SANCIONADO SI NO MUESTRA ERROR
    } // BOTON PARA MODIFICAR EL ATRIBUTO SANCIONADO DE UN EQUIPO SELECCIONADO


    // 1- Los datos en el Combo Box se cargarán del fichero Json que os he adjuntado “Categorias” solo se cargara el nombre.
    private void convertirFichero() {
        ArrayList nombreCategorias = new ArrayList<>(); // CREAR UN ARRAYLIST PARA GUARDAR EL NOMBRE DE LA CATEGORIA
        ObjectMapper JSON_MAPPER = new ObjectMapper(); // OBJETO MAPPER
        // (DESERIALIZACION)
        try {
            ArrayList<Categoria> categorias =
                    JSON_MAPPER.readValue(new File("src/main/java/org/esteladevega_examen1evaluacion/SQL/Categorias.json"),
                            JSON_MAPPER.getTypeFactory().constructCollectionType
                                    (ArrayList.class, Categoria.class));

            for (Categoria categoria : categorias) {
                nombreCategorias.add(categoria.getNombre()); // AÑADIR NOMBRES
            } // RECORRE LAS CATEGORIAS Y LAS AÑADE

            categoriaCombobox.getItems().addAll(nombreCategorias); // AÑADIR LOS NOMBRES AL COMBOBOX
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // METODO PARA CONVERTIR UN FICHERO JSON A UN ARRAYLIST

    private void refreshTable() {
        equiposTable.setItems(hibernateEquipoDAO.listarEquipos());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        convertirFichero();
        // CONFIGURAR COLUMNAS
        idEquipoCol.setCellValueFactory(new PropertyValueFactory<>("idEquipo"));
        nombreEquipoCol.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));
        patrocinadorCol.setCellValueFactory(new PropertyValueFactory<>("patrocinado"));
        categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        sancionadoCol.setCellValueFactory(new PropertyValueFactory<>("sancionado"));

        refreshTable(); // INSERTAR LOS DATOS EN LA TABLA
    }
}