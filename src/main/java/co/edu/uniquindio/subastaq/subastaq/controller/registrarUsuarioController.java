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

import java.util.Optional;

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
    private TableColumn<UsuarioDto, String> colmUsuario;

    @FXML
    private TableView<UsuarioDto> tableUsuarios;

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
        colmCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        colmUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreUsuario()));
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
    @FXML
    void crearUsuarioAction(ActionEvent event){
        texNombre.setText("Ingrese el nombre");
        texApellido.setText("Ingrese el apellido");
        texCedula.setText("Ingrese la cedula");
        texEdad.setText("Ingrese la edad");
        comboUsuario.setValue(String.valueOf(usuarioSeleccionado.getClass()));
        texUsuario.setText("Ingrese el usuario");
        texContrasena.setText("Ingrese la contraseña");
    }

    @FXML
    void guardarUsuarioAction(ActionEvent event) { crearUsuario(); }



    private void crearUsuario() {
        //1. Capturar los datos
        UsuarioDto usuarioDto = construirUsuarioDto();
        //2. Validar la información
        if(datosValidos(usuarioDto)){
            if(UsuarioControllerService.agregarUsuario(usuarioDto)){
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación Usuario", "Usuario creado", "El usuario se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
                registrarAcciones("Usuario agregado",1, "Agregar Usuario");
            }else{
                mostrarMensaje("Notificación usuario", "Usuario no creado", "El usuario no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación usuario", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
        texNombre.getText(),
        texApellido.getText(),
        texCedula.getText(),
        Integer.valueOf(texEdad.getText()),
        texUsuario.getText(),
        texContrasena.getText()
        );
    }
    private void limpiarCamposUsuario() {
        texNombre.setText("");
        texApellido.setText("");
        texCedula.setText("");
        texEdad.setText("");
        texUsuario.setText("");
        texContrasena.setText("");
    }
    private void registrarAcciones(String mensaje, int nivel, String accion) {
        UsuarioControllerService.registrarAcciones(mensaje, nivel, accion);
    }
    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioDto.apellido() == null || usuarioDto.apellido().equals(""))
            mensaje += "El apellido es invalido \n" ;
        if(usuarioDto.cedula() == null || usuarioDto.cedula().equals(""))
            mensaje += "El documento es invalido \n" ;
        if(usuarioDto.edad() == null || usuarioDto.edad().equals(""))
            mensaje += "La edad es invalida \n" ;
        if(usuarioDto.nombreUsuario() == null || usuarioDto.nombreUsuario().equals(""))
            mensaje += "El nombre de usuario es invalida \n" ;
        if(usuarioDto.contrasenia() == null || usuarioDto.contrasenia().equals(""))
            mensaje += "La contraseña es invalida \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}



