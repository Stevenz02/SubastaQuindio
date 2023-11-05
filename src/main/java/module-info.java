module co.edu.uniquindio.subastaq.subastaq {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.subastaq.subastaq to javafx.fxml;
    exports co.edu.uniquindio.subastaq.subastaq;
}