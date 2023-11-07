package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Comprador;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.UsuarioController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

public class registrarUsuarioController {
    UsuarioController UsuarioControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    private Label accesoLabel;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Label registroLabel;

    @FXML
    private TextField texNombre;

    @FXML
    private TextField texApellido;

    @FXML
    private TextField texCedula;

    @FXML
    private TextField texEdad;

    @FXML
    private ComboBox<String> comboUsuario;

    @FXML
    private TextField texUsuario;

    @FXML
    private PasswordField texContrasena;

    @FXML
    private TableColumn<UsuarioDto, String> colmNombre;

    @FXML
    private TableColumn<UsuarioDto, String> colmCedula;

    @FXML
    private TableColumn<UsuarioDto, String> colmTipo;

    @FXML
    private TableView<UsuarioDto> tableUsuarios;

    @FXML
    void bttActualizar(ActionEvent event) {

    }

    @FXML
    void bttCrear(ActionEvent event) {

    }

    @FXML
    void bttEliminar(ActionEvent event) {

    }

    @FXML
    void bttGuardar(ActionEvent event) {

    }
    void initialize() {
        UsuarioControllerService = new UsuarioController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuarios.getItems().clear();
        tableUsuarios.setItems(listaUsuariosDto);
        listenerSelection();
    }
    private void initDataBinding() {
        colmNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        colmCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        colmTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
    }
    private void obtenerUsuarios() {
        listaUsuariosDto.addAll(UsuarioControllerService.obtenerUsuarios());
    }
    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }
    private void mostrarInformacionUsuario(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            texNombre.setText(usuarioSeleccionado.nombre());
            texApellido.setText(usuarioSeleccionado.apellido());
            texCedula.setText(usuarioSeleccionado.cedula());
            texEdad.setText(String.valueOf(usuarioSeleccionado.edad()));
            comboUsuario.setValue(String.valueOf(usuarioSeleccionado.getClass()));
            texUsuario.setText(usuarioSeleccionado.nombreUsuario());
            texContrasena.setText(usuarioSeleccionado.contrasenia());
        }
    }
}



