package org.example.esteladevega_ejerciciojson.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private ListView<?> listView;

    @FXML
    private TextField titleTxt;
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @FXML
    void onImportarAction(ActionEvent event) {

    }

}
