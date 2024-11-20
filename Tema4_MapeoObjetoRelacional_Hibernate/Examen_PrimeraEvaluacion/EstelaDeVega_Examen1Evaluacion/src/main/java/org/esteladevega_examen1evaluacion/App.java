package org.esteladevega_examen1evaluacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.esteladevega_examen1evaluacion.Utilities.R;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("Equipo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Equipos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}