package com.example.esteladevega_ejercicioformulario;

import com.example.esteladevega_ejercicioformulario.ConnectionDB.ConnectionDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ui/beginning.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CubeX Galaxy!");
        // AÑADIR IMAGEN EN EL STAGE DEL LOGO DE LA APLICACION
        stage.getIcons().add(new Image(this.getClass().getResource("/ui/Imagen/cubeX_GalaxyLogo.png").toString()));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}