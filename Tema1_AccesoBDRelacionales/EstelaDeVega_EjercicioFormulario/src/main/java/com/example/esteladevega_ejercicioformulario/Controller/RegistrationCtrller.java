package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationCtrller {
    @FXML
    private Button backBtt;
    @FXML
    private Button cancelBtt;
    @FXML
    private Button closeBtt;
    @FXML
    private TextField emailTxt;
    @FXML
    private Button goBtt;
    @FXML
    private Button logBtt;
    @FXML
    private Pane logginVision;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button signBtt;

    @FXML
    void onBackAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DEL PRINCIPIO
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/beginning.fxml", backBtt, "CubeX Galaxy!");
    } // IR A LA PAGINA DEL PRINCIPIO

    @FXML
    void onCancelAction(ActionEvent event) {
        emailTxt.clear();
        passwordTxt.clear();
    } // LIMPIAR LOS CAMPOS DEL LOGIN

    @FXML
    void onCloseAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO PARA SALIR DE LA APLICACION
        StaticCode.exitApp();
    } // SALIR DE LA APLICACIÓN

    @FXML
    void onGoAction(ActionEvent event) {

    }

    @FXML
    void onLogInAction(ActionEvent event) {

    }

    @FXML
    void onSignAction(ActionEvent event) {

    }
}
