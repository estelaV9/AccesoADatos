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

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingCtrller implements Initializable {
    @FXML
    private Pane accountPane;
    @FXML
    private Label accountTitle;
    @FXML
    private Button backBtt;
    @FXML
    private Button deleteAccountBtt;
    @FXML
    private Button deleteBtt;
    @FXML
    private Pane deletePane;
    @FXML
    private Button exitBtt;
    @FXML
    private Button githubBtt;
    @FXML
    private Button passwordBtt;
    @FXML
    private Pane passwordPane;
    @FXML
    private Button personalBtt;
    @FXML
    private Pane personalPane;
    @FXML
    private Button profileBtt;
    @FXML
    private Pane settingGMenu;
    @FXML
    private Button signOutBtt;
    @FXML
    private TextField txtEmailUser;
    @FXML
    private TextField txtNameUser;
    @FXML
    private PasswordField txtNewPasswordConfirm;
    @FXML
    private PasswordField txtNewPasswordUp;
    @FXML
    private Button updateInfoBtt;
    @FXML
    private Button updatePsswBtt;
    @FXML
    private Button userManualBtt;

    @FXML
    void onBackAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DE TIENDA
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/CubeShop.fxml", backBtt, "Cube Shop");
    } // IR A LA PAGINA DE TIENDA

    @FXML
    void onDeleteAccAction(ActionEvent event) {

    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

    @FXML
    void onExitAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO PARA SALIR DE LA APLICACION
        StaticCode.exitApp();
    } // SALIR DE LA APLICACIÓN

    @FXML
    void onGithubAction(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/estelaV9"));
            /*SE USA LA CLASE DESKTOP QUE PERMITE HACER COSAS RELACIONADAS CON EL ESCRITORIO DEL ORDENADOR
            getDesktop() ES UN METODO QUE PROPORCIONA UNA INSTANCIA, ES DECIR, UN OBJETO DE LA CLASE DESKTOP.
            EL METODO browse() NOS PERMITE ABRIR UNA URL EN EL NAVEGADOR WEB PREDETERMINADO*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onPasswordAction(ActionEvent event) {

    }

    @FXML
    void onPersonalAction(ActionEvent event) {

    }

    @FXML
    void onProfileAction(ActionEvent event) {

    }

    @FXML
    void onSignOutAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DEL LOGIN
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/Registration.fxml", backBtt, "Registration Page");
    } // IR A LA PAGINA DEL LOGIN

    @FXML
    void onUpdateInfoAction(ActionEvent event) {

    }

    @FXML
    void onUpdatePasswAction(ActionEvent event) {

    }

    @FXML
    void onUserManualAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
