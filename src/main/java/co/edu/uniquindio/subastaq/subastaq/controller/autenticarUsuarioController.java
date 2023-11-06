package co.edu.uniquindio.subastaq.subastaq.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class autenticarUsuarioController {

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnIngresar;

    @FXML
    private PasswordField texContrasena;

    @FXML
    private TextField texUsuario;

    @FXML
    void bttCerrar(ActionEvent event) {
        // Obtener la ventana actual
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }

    @FXML
    void bttIngresar(ActionEvent event) {

    }

}

