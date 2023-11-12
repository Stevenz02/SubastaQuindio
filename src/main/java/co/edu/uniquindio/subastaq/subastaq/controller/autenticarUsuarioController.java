package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.UsuarioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.service.IModelFactoryControllerService;
import co.edu.uniquindio.subastaq.subastaq.exception.UsuarioExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class autenticarUsuarioController {
    ModelFactoryController ModelFactoryControllerService = ModelFactoryController.getInstance();
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

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
    void bttIngresar(ActionEvent event) throws UsuarioExepcion {
        ModelFactoryControllerService.iniciarSesion(texUsuario.getText(), texContrasena.getText(), event);
    }

}

