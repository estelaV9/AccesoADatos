module com.example.ejerciciorepasofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.ejerciciorepasofx to javafx.fxml;
    exports com.example.ejerciciorepasofx;
}