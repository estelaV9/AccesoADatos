package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.Model.Product;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CubeShopCtrller implements Initializable {
    @FXML
    private TableView<Product> CubeTable;

    @FXML
    private Button backBtt;

    @FXML
    private TableColumn<?, ?> categoryCol;

    @FXML
    private Button closeBtt;

    @FXML
    private Button closeSeBtt;

    @FXML
    private Label loginMessage;

    @FXML
    private TableColumn<Product, String> nameProductCol;

    @FXML
    private Button newProductBtt;

    @FXML
    private TableColumn<Product, String> ownerCol;

    @FXML
    private TableColumn<Product, Double> priceCol;

    @FXML
    private Button settingBtt;

    @FXML
    private Pane settingMenu;

    @FXML
    private Button settingsMenuBtt;

    @FXML
    private Button shopCartBtt;

    @FXML
    private Button yourProductBtt;


    // ATRIBUTOS SEMAFOROS PARA ABRIR Y CERRAR DESDE EL MISMO BOTON
    boolean pulsarOption = false;

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
    void onCloseSettingAction() {
        settingMenu.setVisible(false);
    }

    @FXML
    void onNewProductAction(ActionEvent event) {

    }

    @FXML
    void onSettingAction(ActionEvent event) {
        if (!pulsarOption) { // SI NO SE HA PULSADO, SE ABRE EL MENU
            settingMenu.setVisible(true);
            pulsarOption = true;
        } else { // SI SE HA PULSADO, SE CIERRA EL MENU
            onCloseSettingAction();
            pulsarOption = false;
        }
    } // SE ABRE UN POPUP PARA ACCEDER A LOS AJUSTES

    @FXML
    void onSettingsMenuAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DE AJUSTES
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/Setting.fxml", settingBtt, "Setting Page");
    } // IR A LA PAGINA DE SETTINGS

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
