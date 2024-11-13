package org.esteladevega_cochemultahibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ui/Coche.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Multas");
        // AÑADIR IMAGEN EN EL STAGE DEL LOGO DE LA APLICACION
        stage.getIcons().add(new Image(this.getClass().getResource("/ui/Image/logoGestionCoches.png").toString()));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}