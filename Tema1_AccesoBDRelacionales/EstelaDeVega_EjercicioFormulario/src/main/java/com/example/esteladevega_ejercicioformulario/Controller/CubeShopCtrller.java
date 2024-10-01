package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CubeShopCtrller implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backBtt;

    @FXML
    private Button closeBtt;

    @FXML
    private Button closeSeBtt;

    @FXML
    private Label loginMessage;

    @FXML
    private Button newProductBtt;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button settingBtt;

    @FXML
    private Pane settingMenu;

    @FXML
    private Button settingsMenuBtt;

    @FXML
    private Button shopCartBtt;

    @FXML
    private Button signOutBtt;

    @FXML
    private Button yourProductBtt;

    @FXML
    void onBackAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DEL LOGIN
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/Registration.fxml", backBtt, "Registration Page");
    } // IR A LA PAGINA DEL LOGIN

    @FXML
    void onCloseAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO PARA SALIR DE LA APLICACION
        StaticCode.exitApp();
    } // SALIR DE LA APLICACIÓN

    @FXML
    void onCloseSettingAction(ActionEvent event) {
        settingMenu.setVisible(false);
    }

    @FXML
    void onNewProductAction(ActionEvent event) {

    }

    @FXML
    void onSettingAction(ActionEvent event) {
        settingMenu.setVisible(true);
    }

    @FXML
    void onSettingsMenuAction(ActionEvent event) {

    }

    @FXML
    void onShopCartAction(ActionEvent event) {

    }

    @FXML
    void onYourProductAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingMenu.setVisible(false);
    }
}
