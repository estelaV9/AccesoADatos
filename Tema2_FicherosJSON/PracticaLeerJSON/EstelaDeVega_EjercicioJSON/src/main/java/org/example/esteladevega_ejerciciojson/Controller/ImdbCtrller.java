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

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();


    @FXML
    void onImportarAction(ActionEvent event) {
        try {
            // CONVERTIR EL FICHERO JSON A ARRAYLIST
            ArrayList<Pelicula> peliculas =
                    JSON_MAPPER.readValue(new File("src/main/resources/json/peliculas.json"),
                            JSON_MAPPER.getTypeFactory().constructCollectionType
                                    (ArrayList.class, Pelicula.class));

            // SE CREA UN OBSERVABLE LIST DE TIPO PELICULAS Y SE AÑADE EL ARRAYLIST DE PELICULAS
            ObservableList<Pelicula> items = FXCollections.observableArrayList(peliculas);
            listView.setItems(items); // SE AÑADE LOS ITEMS A LISTAVIEW

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("¡Error al importar!");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }



    @FXML
    void onMostrarPeliculasClick(MouseEvent event) {
        Pelicula pelicula = listView.getSelectionModel().getSelectedItem();
        titleTxt.setText(pelicula.getTitulo());
        dateTxt.setText(pelicula.getFecha());
        directorTxt.setText(pelicula.getDirector());
        genderTxt.setText(pelicula.getGenero());
    }
}
