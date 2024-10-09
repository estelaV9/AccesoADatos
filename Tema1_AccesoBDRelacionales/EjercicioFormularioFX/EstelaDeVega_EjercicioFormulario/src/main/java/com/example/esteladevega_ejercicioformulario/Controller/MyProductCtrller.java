package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.ConnectionDB.ConnectionDB;
import com.example.esteladevega_ejercicioformulario.DAO.ProductDAO;
import com.example.esteladevega_ejercicioformulario.Model.Product;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MyProductCtrller implements Initializable {

    @FXML
    private TableView<Product> CubeTable;

    @FXML
    private Button backBtt;

    @FXML
    private TableColumn<String, Product> categoryCol;

    @FXML
    private Button closeBtt;

    @FXML
    private Button closeSeBtt;

    @FXML
    private Button deleteBtt;

    @FXML
    private Label loginMessage;

    @FXML
    private Button modifyBtt;

    @FXML
    private TableColumn<String, Product> nameProductCol;

    @FXML
    private TableColumn<Integer, Product> priceCol;

    @FXML
    private Button settingBtt;

    @FXML
    private Pane settingMenu;

    @FXML
    private Button settingsMenuBtt;
    // ATRIBUTOS SEMAFOROS PARA ABRIR Y CERRAR DESDE EL MISMO BOTON
    boolean pulsarOption = false;

    @FXML
    void onBackAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DE TIENDA
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/CubeShop.fxml", backBtt, "Cube Shop");
    } // VOLVER A LA PAGINA DE TIENDA

    @FXML
    void onCloseAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO PARA SALIR DE LA APLICACION
        StaticCode.exitApp();
    } // SALIR DE LA APLICACIÓN

    @FXML
    void onCloseSettingAction() {
        settingMenu.setVisible(false);
    }

    private boolean isSelectedProduct () {
        Product product = CubeTable.getSelectionModel().getSelectedItem(); // SE GUARDA EL OBJETO PRODUCTO SELECCIONADO
        if (product == null) {
            // MOSTRAR ALERTA EN CASO DE QUE SE SELECCIONE UNA FILA VACIA
            StaticCode.Alerts("ERROR", "Producto vacío", "ERROR",
                    "No has seleccionado ningún producto.\nPor favor, elija un prodcuto para acceder a sus opciones.");
            return false;
        }
        return true;
    }


    @FXML
    void onModifyAction() {
        isSelectedProduct();
        if(isSelectedProduct()){
            
        }
    }

    @FXML
    void onRowClicked(MouseEvent event) {
        isSelectedProduct();
        if(isSelectedProduct()){
            // LLAMAR AL METODO MODIFY
            onModifyAction();
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingMenu.setVisible(false);

        // CONFIGURAR COLUMNAS
        nameProductCol.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        // AÑADIR LOS DATOS A UN OBSERVABLELIST
        ObservableList<Product>  listProduct =
                FXCollections.observableArrayList(ProductDAO.myListProduct(ConnectionDB.con, RegistrationCtrller.cubeUser.getMail()));
        CubeTable.setItems(listProduct); // ESTABLECER LISTA
    }
}
