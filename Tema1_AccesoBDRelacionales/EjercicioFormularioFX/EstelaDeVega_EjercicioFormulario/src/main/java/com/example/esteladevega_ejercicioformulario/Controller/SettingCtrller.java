package com.example.esteladevega_ejercicioformulario.Controller;

import com.example.esteladevega_ejercicioformulario.ConnectionDB.ConnectionDB;
import com.example.esteladevega_ejercicioformulario.DAO.CubeUserDAO;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import com.example.esteladevega_ejercicioformulario.Validator.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.commons.codec.digest.DigestUtils;
import javax.swing.*;
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

    String mailUser, contraseñaCifrada;

    @FXML
    void onBackAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DE TIENDA
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/CubeShop.fxml", backBtt, "Cube Shop");
    } // IR A LA PAGINA DE TIENDA

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
    } // METODO QUE REDIRIGE A MI PERFIL DE GITHUB

    @FXML
    void onSignOutAction(ActionEvent event) {
        // SE LLAMA AL METODO ESTATICO CAMBIAR VISTA POR BOTON PARA IR A LA PAGINA DEL LOGIN
        // SE INSERTA LOS PARAMETROS: NOMBRE DEL FXML AL QUE SE QUIERE IR, UN BOTON Y
        // EL TITULO QUE VA A TENER ESE STAGE
        StaticCode.cambiarVistaBtt("/ui/Registration.fxml", backBtt, "Registration Page");
    } // IR A LA PAGINA DEL LOGIN

    @FXML
    void onProfileAction(ActionEvent event) {
        accountPane.setVisible(true);
        personalPane.setStyle("-fx-background-color : #cc87e0");
        personalPane.setVisible(true);
        passwordPane.setVisible(false);
        deletePane.setVisible(false);
        personalBtt.setStyle("-fx-background-color: #cc87e0; -fx-border-color: #000; -fx-text-fill: #000");
        passwordBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        deleteBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
    } // BOTON PARA CAMBIAR AL PANEL DE PERSONAL INFO EN PROFILE

    @FXML
    void onPasswordAction(ActionEvent event) {
        passwordBtt.setStyle("-fx-background-color: #cc87e0; -fx-border-color: #000; -fx-text-fill: #000");
        personalBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        deleteBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        personalPane.setVisible(false);
        passwordPane.setVisible(true);
        deletePane.setVisible(false);
    } // BOTON PARA CAMBIAR AL PANEL DE CONTRASEÑA EN PROFILE

    @FXML
    void onPersonalAction(ActionEvent event) {
        personalBtt.setStyle("-fx-background-color: #cc87e0; -fx-border-color: #000; -fx-text-fill: #000");
        passwordBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        deleteBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        personalPane.setVisible(true);
        passwordPane.setVisible(false);
        deletePane.setVisible(false);
    } // BOTON PARA MOSTRAR EL PANEL DE PROFILE (POR DEFECTO SE INICIARA EN PERSONAL INFO)

    @FXML
    void onDeleteAction(ActionEvent event) {
        deleteBtt.setStyle("-fx-background-color: #cc87e0; -fx-border-color: #000; -fx-text-fill: #000");
        passwordBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        personalBtt.setStyle("-fx-background-color: #781ddd; -fx-border-color: #000; -fx-text-fill: #000");
        personalPane.setVisible(false);
        passwordPane.setVisible(false);
        deletePane.setVisible(true);
    } // BOTON PARA CAMBIAR AL PANEL DE DELETE EN PROFILE

    @FXML
    void onDeleteAccAction(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar la cuenta?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            if (CubeUserDAO.deleteUser(ConnectionDB.con, RegistrationCtrller.cubeUser.getMail())) {
                // SI SE ELIMINA EL USUARIO SE MUESTRA EL MENSAJE
                StaticCode.Alerts("INFORMATION", "Eliminación de usuario", "Eliminación exitosa",
                        "Se ha eliminado el usuario correctamente");
                // UNA VEZ ELIMINADO EL USUARIO, VOLVERA A LA PAGINA DE INICIAR SESION
                StaticCode.cambiarVistaBtt("/ui/Registration.fxml", deleteBtt, "Registration Page");
            } else {
                // SI NO SE ENCONTRO USUARIO SE MUESTRA UN MENSAJE (POR SI ACASO)
                StaticCode.Alerts("ERROR", "Error al eliminar usuario", "¡ERROR!",
                        "No se encontró el usuario para eliminar.");
            } // LLAMAR AL METODO ELIMINAR USUARIO
        } // SI EL USUARIO HA ELEGIDO QUE SI QUE QUIERE ELIMINAR ENTRA
    } // METODO PARA ELIMINAR USUARIO

    @FXML
    void onUpdateInfoAction(ActionEvent event) {
        if (txtNameUser.getText().isEmpty() || txtEmailUser.getText().isEmpty()) {
            // SI LOS CAMPOS ESTAN VACIOS, SE MOSTRARA UN ERROR
            StaticCode.Alerts("ERROR", "Campos vacíos", "¡ERROR!",
                    "Por favor, completa todos los campos antes de continuar.");
        } else if (!Validator.isValidMail(txtEmailUser.getText())) {
            // SI EL MAIL NO ES CORRECTO MOSTRARA UN MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Email no válida", "¡ERROR!",
                    "Por favor, introduzca un correo válido:\nexample@example.com");
        } else if (CubeUserDAO.isExistsNameUser(ConnectionDB.con, txtNameUser.getText(), RegistrationCtrller.cubeUser.getMail())) {
            // SI EL NOMBRE DE USUARIO YA EXISTE, MOSTRAR UN MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Nombre no válido", "¡ERROR!",
                    "Ese nombre ya esta en uso. Por favor, pruebe con otro");
        } else {
            if (!CubeUserDAO.modifyUser(ConnectionDB.con, txtNameUser.getText(), txtEmailUser.getText(), RegistrationCtrller.cubeUser.getMail())) {
                // SI NO SE HA MODIFICADO CORRECTAMENTE, LANZA UN MENSAJE DE ERROR
                StaticCode.Alerts("ERROR", "Error al modificar usuario", "¡ERROR!",
                        "No se ha podido modificar el usuario.");
            } else {
                StaticCode.Alerts("INFORMATION", "Modificación de usuario", "Modificación exitosa",
                        "Se ha modificado el usuario correctamente");
            }
        }
    } // METODO PARA MODIFICAR USUARIO

    @FXML
    void onUpdatePasswAction(ActionEvent event) {
        if (txtNewPasswordUp.getText().isEmpty() || txtNewPasswordConfirm.getText().isEmpty()) {
            // SI LOS CAMPOS ESTAN VACIOS, SE MOSTRARA UN ERROR
            StaticCode.Alerts("ERROR", "Campos vacíos", "¡ERROR!",
                    "Por favor, completa todos los campos antes de continuar.");
        } else if (!txtNewPasswordUp.getText().equals(txtNewPasswordConfirm.getText())) {
            // SI LAS CONTRASEÑAS NO COINCIDEN, SE MOSTRARA UN ERROR
            StaticCode.Alerts("ERROR", "Contraseñas no coinciden", "¡ERROR!",
                    "Las contraseñas no coinciden. Por favor, verifica e intenta nuevamente.");
        } else if (Validator.isValidPassword(txtNewPasswordUp.getText())) {
            contraseñaCifrada = DigestUtils.sha256Hex(txtNewPasswordUp.getText()); // CIFRAR CONTRASEÑA
            if (CubeUserDAO.modifyPassword(ConnectionDB.con, contraseñaCifrada, RegistrationCtrller.cubeUser.getMail())) {
                // SI SE ACTUALIZO EL USUARIO, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("INFORMATION", "Actualización de contraseña", "Actualizacion exitosa",
                        "Se ha actualizado la contraseña correctamente.");
            } else {
                // SI NO SE ACTUALIZO, MOSTRAR UN MENSAJE DE EXITO
                StaticCode.Alerts("ERROR", "Error al modificar usuario", "¡ERROR!",
                        "No se pudo modificar la contraseña");
            }
        } else {
            // SI LA CONTRASEÑA NO ES CORRECTA MOSTRARA UN MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Contraseña no válida", "¡ERROR!",
                    "Por favor, introduzca una contraseña válida:\nPs.contains(8)");
        }
    }

    @FXML
    void onUserManualAction(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/estelaV9/AccesoADatos/blob/master/Tema1_AccesoBDRelacionales/EjercicioFormularioFX/PracticaFormulario.md"));
            /*SE USA LA CLASE DESKTOP QUE PERMITE HACER COSAS RELACIONADAS CON EL ESCRITORIO DEL ORDENADOR
            getDesktop() ES UN METODO QUE PROPORCIONA UNA INSTANCIA, ES DECIR, UN OBJETO DE LA CLASE DESKTOP.
            EL METODO browse() NOS PERMITE ABRIR UNA URL EN EL NAVEGADOR WEB PREDETERMINADO*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // METODO QUE REDIRIGE A UN RESUMEN DE LA APLICACION CON TODAS SUS VISTAS

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // AL INICIAR LA VISTA, EL PANE DE CUENTA NO ESTARA VISIBLE HASTA QUE SE PULSE EL BOTON DE PROFILE
        accountPane.setVisible(false);
    }
}
