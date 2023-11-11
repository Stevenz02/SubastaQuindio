module co.edu.uniquindio.subastaq.subastaq {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mapstruct;
    requires java.desktop;
    requires java.logging;

    opens co.edu.uniquindio.subastaq.subastaq.aplicacion to javafx.fxml;
    opens co.edu.uniquindio.subastaq.subastaq.controller to javafx.fxml;
    exports co.edu.uniquindio.subastaq.subastaq.aplicacion to javafx.graphics;
    exports co.edu.uniquindio.subastaq.subastaq.mapping.mappers to org.mapstruct;
}