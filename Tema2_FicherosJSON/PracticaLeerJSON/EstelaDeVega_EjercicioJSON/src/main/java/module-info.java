module org.example.esteladevega_ejerciciojson {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.esteladevega_ejerciciojson to javafx.fxml;
    exports org.example.esteladevega_ejerciciojson;
}