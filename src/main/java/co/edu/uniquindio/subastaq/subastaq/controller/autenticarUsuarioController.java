package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.exception.BuscarUsuarioExepcion;
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

    /**
     * Cierra la ventana actual de la interfaz gráfica de usuario.
     *
     * @param event El evento generado al hacer clic en el botón 'Cerrar'.
     */
    @FXML
    void bttCerrar(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el proceso de inicio de sesión con credenciales de usuario.
     *
     * @param event El evento generado al hacer clic en el botón 'Ingresar'.
     *              Intenta iniciar sesión con el nombre de usuario y contraseña.
     *              Captura y registra excepciones relacionadas con la búsqueda de usuarios.
     */
    @FXML
    void bttIngresar(ActionEvent event) {
        try {
            ModelFactoryControllerService.iniciarSesion(texUsuario.getText(), texContrasena.getText(), event);
        } catch (BuscarUsuarioExepcion e) {
            e.printStackTrace();
        }
    }
}

