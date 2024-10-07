package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.ConnectionDB.ConnectionDB;
import com.example.esteladevega_ejercicioformulario.DAO.CubeUserDAO;
import com.example.esteladevega_ejercicioformulario.Model.CubeUser;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import com.example.esteladevega_ejercicioformulario.Validator.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public static CubeUser cubeUser;


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
        if(emailTxt.getText().isEmpty() || passwordTxt.getText().isEmpty()){
            // SI LOS CAMPOS ESTAN VACIOS, SE MOSTRARA UN ERROR
            StaticCode.Alerts("ERROR", "Campos vacíos", "¡ERROR!",
                    "Por favor, completa todos los campos antes de continuar.");
        } else if (!Validator.isValidMail(emailTxt.getText())) {
            // SI EL EMAIL NO ES CORRECTO MOSTRARA UN MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Correo no válido", "¡ERROR!",
                    "Por favor, introduzca un correo válido:\nexample@example.com");
        } else if (!Validator.isValidPassword(passwordTxt.getText())) {
            // SI LA CONTRASEÑA NO ES CORRECTA MOSTRARA UN MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Contraseña no válida", "¡ERROR!",
                    "Por favor, introduzca una contraseña válida:\nPs.contains(8)");
        } else {
            cubeUser = new CubeUser(emailTxt.getText(), passwordTxt.getText()); // SE CREA UN USUARIO
            if(!CubeUserDAO.isExistsUser(ConnectionDB.con, cubeUser)) {
                // SI NO EXISTE EL USUARIO MOSTRAR UN MENSAJE EN LA VISTA DE ERROR
                loginMessage.setText("Invalid login");
            } else {
                // SI EXISTE EL USUARIO, REDIRIGIRA A LA PAGINA D ETIENDA
                StaticCode.cambiarVistaBtt("/ui/CubeShop.fxml", logBtt, "Cube Shop");
            }
        }
    } // METODO LOGIN PARA INICIAR SESION E IR A LA PAGINA DE TIENDA

    @FXML
    void onLogViewAction(ActionEvent event) {
        signUpVision.setVisible(false);
        logginVision.setVisible(true);
    } // MOSTRAR LA VISTA DE LOGIN

    @FXML
    void onSignUpAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        LocalDate currentDate = LocalDate.now();
        if (userNameTxt.getText().isEmpty() || emailSignTxt.getText().isEmpty() || passwordSignTxt.getText().isEmpty()
                || confirmPssTxt.getText().isEmpty()) {
            StaticCode.Alerts("ERROR", "Campos vacíos", "¡ERROR!",
                    "Por favor, completa todos los campos antes de continuar.");
        } else if (!passwordSignTxt.getText().equals(confirmPssTxt.getText())) {
            StaticCode.Alerts("ERROR", "Contraseñas no coinciden", "¡ERROR!",
                    "Las contraseñas no coinciden. Por favor, verifica e intenta nuevamente.");
        } else if (!Validator.isValidMail(emailSignTxt.getText())) {
            StaticCode.Alerts("ERROR", "Correo no válido", "¡ERROR!",
                    "Por favor, introduzca un correo válido:\nexample@example.com");
        } else if (!Validator.isValidPassword(passwordSignTxt.getText())) {
            StaticCode.Alerts("ERROR", "Contraseña no válida", "¡ERROR!",
                    "Por favor, introduzca una contraseña válida:\nPs.contains(8)");
        } else {
            cubeUser = new CubeUser(userNameTxt.getText(), passwordSignTxt.getText(), 0, emailSignTxt.getText(), currentDate);
            if(CubeUserDAO.insertUser(ConnectionDB.con, cubeUser)){
                // SI SE INSERTO EL USUARIO, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Creación de usuario", "Creación exitosa",
                        "Se ha creado el usuario correctamente.");
                // IR A LA PAGINA DE TIENDA DESPUES DE HABER CREADO EL USUARIO
                StaticCode.cambiarVistaBtt("/ui/CubeShop.fxml", signBtt, "Cube Shop");
            } else {
                StaticCode.Alerts("ERROR", "Creación de usuario", "Creación fallida",
                        "No se ha podido crear el usuario.");
            }
        }
    } // METODO PARA CREAR UNA CUENTA

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