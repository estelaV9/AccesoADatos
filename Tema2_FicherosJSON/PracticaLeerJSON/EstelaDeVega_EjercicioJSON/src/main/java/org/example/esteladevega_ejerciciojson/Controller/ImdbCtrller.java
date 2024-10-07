package org.example.esteladevega_ejerciciojson.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.esteladevega_ejerciciojson.Model.Pelicula;
import org.example.esteladevega_ejerciciojson.Utilities.StaticCode;

import java.io.File;
import java.util.ArrayList;

public class ImdbCtrller {
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField directorTxt;
    @FXML
    private TextField genderTxt;
    @FXML
    private Button importarBtt;
    @FXML
    private ListView<Pelicula> listView;
    @FXML
    private TextField titleTxt;

    // CREAR UN OBJETO DE ObjectMapper PARA MANEJAR LA CONVERSION DE JSON A OBJETOS
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();


    @FXML
    void onImportarAction(ActionEvent event) {
        try {
            // CONVERTIR EL FICHERO JSON A ARRAYLIST
            /** lee el archivo JSON y lo convierte a una lista de objetos Pelicula **/
            ArrayList<Pelicula> peliculas =
                    JSON_MAPPER.readValue(new File("src/main/resources/json/peliculas.json"),
                            JSON_MAPPER.getTypeFactory().constructCollectionType
                                    (ArrayList.class, Pelicula.class));

            // SE CREA UN OBSERVABLE LIST DE TIPO PELICULAS Y SE AÑADE EL ARRAYLIST DE PELICULAS
            ObservableList<Pelicula> items = FXCollections.observableArrayList(peliculas);
            listView.setItems(items); // SE AÑADE LOS ITEMS A LISTVIEW

        } catch (Exception ex) {
            // MOSTRAR ALERTA EN CASO DE ERROR
            StaticCode.Alerts("ERROR", "Error al importar", "ERROR",
                    "Error: " + ex.getMessage());
        }
    } // MOSTRAR LAS PELICULAS EN LA LISTA CUANDO SE PULSA EL BOTON DE IMPORTAR


    @FXML
    void onMostrarPeliculasClick(MouseEvent event) {
        Pelicula pelicula = listView.getSelectionModel().getSelectedItem(); // SE GUARDA EL OBJETO PELICULA SELECCIONADO
        if (pelicula == null) {
            // MOSTRAR ALERTA EN CASO DE QUE SE SELECCIONE UNA FILA VACIA
            StaticCode.Alerts("ERROR", "Pelicula nula", "ERROR",
                    "No has seleccionado ninguna pelicula.\nPor favor, elija una pelicula para ver sus datos.");
        } else {
            // SE SETTEAN LOS DATOS EN LOS TEXTFIELDS
            titleTxt.setText(pelicula.getTitulo());
            dateTxt.setText(pelicula.getFecha());
            directorTxt.setText(pelicula.getDirector());
            genderTxt.setText(pelicula.getGenero());
        }
    } // SETTEAR LOS DATOS EN LOS TEXTFIELD CUANDO SE SELECCIONA UNA PELICULA
}
