package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationCtrller implements Initializable {
    @FXML
    private Button backBtt;
    @FXML
    private Button cancelBtt;
    @FXML
    private Button closeBtt;
    @FXML
    private PasswordField confirmPssTxt;
    @FXML
    private TextField emailSignTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private Button logBtt;
    @FXML
    private Button logSBtt;
    @FXML
    private Pane logginVision;
    @FXML
    private Label loginMessage;
    @FXML
    private Label loginMessage1;
    @FXML
    private PasswordField passwordSignTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button signBtt;
    @FXML
    private Pane signUpVision;
    @FXML
    private TextField userNameTxt;

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
    void onLogInAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DE TIENDA
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/CubeShop.fxml", logBtt, "Cube Shop");
    } // IR A LA PAGINA DE TIENDA

    @FXML
    void onLogViewAction(ActionEvent event) {
        signUpVision.setVisible(false);
        logginVision.setVisible(true);
    } // MOSTRAR LA VISTA DE LOGIN

    @FXML
    void onSignUpAction(ActionEvent event) {

    }

    @FXML
    void onSignViewAction(ActionEvent event) {
        signUpVision.setVisible(true);
        logginVision.setVisible(false);
    } // MOSTRAR LA VISTA DE SIGN UP

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpVision.setVisible(false);
    }
}
