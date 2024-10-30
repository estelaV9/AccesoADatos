module org.esteladevega_crudcocheshibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.esteladevega_crudcocheshibernate to javafx.fxml;
    exports org.esteladevega_crudcocheshibernate;
}