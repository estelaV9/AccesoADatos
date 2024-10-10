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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class MyProductCtrller implements Initializable {
    @FXML
    private TableView<Product> CubeTable;
    @FXML
    private TableColumn<String, Product> categoryCol;
    @FXML
    private TableColumn<String, Product> nameProductCol;
    @FXML
    private TableColumn<Integer, Product> priceCol;
    @FXML
    private Button ExitMenuBtt;
    @FXML
    private Button backBtt;
    @FXML
    private Button closeBtt;
    @FXML
    private Button closeSeBtt;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button deleteBtt;
    @FXML
    private TextField newProductName;
    @FXML
    private TextField newProductPrice;
    @FXML
    private Label loginMessage;
    @FXML
    private Label loginMessage1;
    @FXML
    private Button modifyBtt;
    @FXML
    private Pane modifyPane;
    @FXML
    private Button modifyProductPaneBtt;
    @FXML
    private Button settingBtt;
    @FXML
    private Pane settingMenu;
    @FXML
    private Button settingsMenuBtt;


    // ATRIBUTOS SEMAFOROS PARA ABRIR Y CERRAR DESDE EL MISMO BOTON
    boolean pulsarOption = false;

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

    @FXML
    void onExitMenuBtt(ActionEvent event) {
        modifyPane.setVisible(false);
    }

    @FXML
    void onModifyProductAction(ActionEvent event) {
        if(newProductName.getText().isEmpty() || newProductPrice.getText().isEmpty() || comboBox.getValue() == null) {
            // SI LOS CAMPOS ESTAN VACIOS, SE MOSTRARA UN ERROR
            StaticCode.Alerts("ERROR", "Campos vacíos", "¡ERROR!",
                    "Por favor, completa todos los campos antes de continuar.");
        } else if(ProductDAO.isExistsNameUser(ConnectionDB.con, newProductName.getText())){
            // CONTROLAR QUE NO PONGA EL MISMO NOMBRE DEL PRODUCTO
            StaticCode.Alerts("ERROR", "Nombre de producto YA existente", "¡ERROR!",
                    "Ese nombre ya esta en uso. Por favor, elija otro nombre.");
        } else {
            // SI SE ACTUALIZO EL PRODUCTO, MOSTRAR UN MENSAJE DE EXITO
            StaticCode.Alerts("INFORMATION", "Actualización de producto", "Actualizacion exitosa",
                    "Se ha actualizado el producto correctamente.");
        }
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

    @FXML
    void onModifyAction() {
        if(isSelectedProduct()){
            modifyPane.setVisible(true);
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
        // AL INICAR, NO SE VISUALIZA LOS PANELES DE SETTINGS Y MODIFY
        settingMenu.setVisible(false);
        modifyPane.setVisible(false);

        // CONFIGURAR COLUMNAS
        nameProductCol.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        // AÑADIR LOS DATOS A UN OBSERVABLELIST
        ObservableList<Product>  listProduct =
                FXCollections.observableArrayList(ProductDAO.myListProduct(ConnectionDB.con, RegistrationCtrller.cubeUser.getMail()));
        CubeTable.setItems(listProduct); // ESTABLECER LISTA

        // INICIALIZAR EL COMBOBOX
        comboBox.getItems().addAll(
                "2x2x2", "3x3x3", "4x4x4", "5x5x5", "6x6x6", "7x7x7",
                "PYRAMINX", "MEGAMINX", "SKEWB", "SQUARE-1", "CLOCK",
                "3x3x3 MIRROR", "PYRAMORPHIX", "MASTERMORPHIX"
        ); // AÑADIR LOS VALORES AL COMBOBOX
    }
}
