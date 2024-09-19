module com.example.ejerciciorepasofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.ejerciciorepasofx to javafx.fxml;
    exports com.example.ejerciciorepasofx;
    exports com.example.ejerciciorepasofx.Controller;
    opens com.example.ejerciciorepasofx.Controller to javafx.fxml;
}