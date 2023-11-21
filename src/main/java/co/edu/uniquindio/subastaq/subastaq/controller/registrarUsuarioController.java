package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.aplicacion.aplicacion;
import co.edu.uniquindio.subastaq.subastaq.model.Anunciante;
import co.edu.uniquindio.subastaq.subastaq.model.Comprador;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import co.edu.uniquindio.subastaq.subastaq.model.Usuario;
import co.edu.uniquindio.subastaq.subastaq.utils.Persistencia;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.UsuarioController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class registrarUsuarioController {
    UsuarioController UsuarioControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    /**
     * Método inicializador que se ejecuta al cargar la interfaz de usuario.
     * Configura los controladores y prepara la vista inicial.
     */
    @FXML
    void initialize() {
        UsuarioControllerService = new UsuarioController();
        intiView();
    }
    /**
     * Inicializa la vista principal de la aplicación.
     * Configura la vinculación de datos y carga los usuarios para mostrar en la tabla.
     */
    private void intiView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuarios.getItems().clear();
        tableUsuarios.setItems(listaUsuariosDto);
        listenerSelection();
    }
    /**
     * Configura el enlace de datos entre la interfaz de usuario y los modelos de datos.
     * Establece cómo se mostrarán los datos en las columnas de la tabla de usuarios.
     */
    private void initDataBinding() {
        colmNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        colmCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        colmUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreUsuario()));
    }
    /**
     * Obtiene y agrega los usuarios al modelo de datos observable para mostrar en la interfaz.
     */
    private void obtenerUsuarios() {
        listaUsuariosDto.addAll(UsuarioControllerService.obtenerUsuarios());
    }
    /**
     * Añade un listener a la selección en la tabla de usuarios.
     * Actualiza la información mostrada en la interfaz de usuario basada en el usuario seleccionado.
     */
    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }
    /**
     * Muestra la información del usuario seleccionado en los campos de texto de la interfaz.
     * @param usuarioSeleccionado El usuario seleccionado en la tabla.
     */
    private void mostrarInformacionUsuario(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            texNombre.setText(usuarioSeleccionado.nombre());
            texApellido.setText(usuarioSeleccionado.apellido());
            texCedula.setText(usuarioSeleccionado.cedula());
            texEdad.setText(String.valueOf(usuarioSeleccionado.edad()));
            comboUsuario.setValue(String.valueOf(usuarioSeleccionado.tipo()));
            texUsuario.setText(usuarioSeleccionado.nombreUsuario());
            texContrasena.setText(usuarioSeleccionado.contrasenia());
        }
    }
    /**
     * Prepara los campos de texto para crear un nuevo usuario.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    void crearUsuarioAction(ActionEvent event){
        texNombre.setText("Ingrese el nombre");
        texApellido.setText("Ingrese el apellido");
        texCedula.setText("Ingrese la cedula");
        texEdad.setText("Ingrese la edad");
        comboUsuario.setValue("Seleccione un tipo");
        texUsuario.setText("Ingrese el usuario");
        texContrasena.setText("Ingrese la contraseña");
    }
    /**
     * Gestiona el guardado de un nuevo usuario en el sistema.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    void guardarUsuarioAction(ActionEvent event) { crearUsuario(); }
    /**
     * Gestiona la actualización de un usuario existente en el sistema.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    void actualizarUsuarioAction(ActionEvent event) { actualizarUsuario(); }
    /**
     * Gestiona la eliminación de un usuario del sistema.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    void eliminarUsuarioAction(ActionEvent event) { eliminarUsuario(); }
    /**
     * Crea un nuevo usuario a partir de los datos ingresados en la interfaz.
     * Valida los datos y los agrega al sistema si son válidos.
     */
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
    /**
     * Actualiza los datos de un usuario existente en el sistema.
     * Valida los datos nuevos y realiza la actualización si son válidos.
     */
    private void actualizarUsuario() {
        if (usuarioSeleccionado != null) {
            //1. Capturar los datos nuevos
            UsuarioDto usuarioDto = construirUsuarioDto();

            //2. Validar la información nueva
            if (datosValidos(usuarioDto)) {
                boolean usuarioActualizado = UsuarioControllerService.actualizarUsuario(usuarioSeleccionado.cedula(), usuarioDto);

                if (usuarioActualizado) {
                    // Encuentra el índice del usuario seleccionado en la lista y actualiza la lista.
                    int selectedIndex = listaUsuariosDto.indexOf(usuarioSeleccionado);
                    if(selectedIndex != -1) {
                        listaUsuariosDto.set(selectedIndex, usuarioDto); // Actualizar la lista con los nuevos datos.
                    }
                    tableUsuarios.refresh();
                    mostrarMensaje("Notificación usuario", "Usuario actualizado", "El usuario se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposUsuario();
                } else {
                    mostrarMensaje("Notificación usuario", "Usuario no actualizado", "El usuario no se ha actualizado con éxito", Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje("Notificación usuario", "Datos inválidos", "Los datos ingresados son inválidos", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación usuario", "Usuario no seleccionado", "Seleccione un usuario de la lista", Alert.AlertType.WARNING);
        }
    }
    /**
     * Elimina un usuario seleccionado del sistema después de confirmar la acción.
     */
    private void eliminarUsuario() {
        if(usuarioSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar al usuario?")){
                boolean usuarioEliminado = UsuarioControllerService.eliminarUsuario(usuarioSeleccionado.cedula());
                if(usuarioEliminado){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    tableUsuarios.getSelectionModel().clearSelection();
                    mostrarMensaje("Notificación usuario", "Usuario eliminado", "El usuario se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposUsuario();
                }else{
                    mostrarMensaje("Notificación usuario", "Usuario no eliminado", "El usuario no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación usuario", "Usuario no seleccionado", "Selecciona un usuario de la lista", Alert.AlertType.WARNING);
        }
    }
    /**
     * Construye un objeto UsuarioDto a partir de los datos ingresados en la interfaz.
     * @return UsuarioDto con los datos ingresados.
     */
    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
        texNombre.getText(),
        texApellido.getText(),
        texCedula.getText(),
        Integer.valueOf(texEdad.getText()),
        texUsuario.getText(),
        texContrasena.getText(),
        comboUsuario.getValue()
        );
    }
    /**
     * Limpia los campos de entrada del formulario de usuario.
     */
    private void limpiarCamposUsuario() {
        texNombre.setText("");
        texApellido.setText("");
        texCedula.setText("");
        texEdad.setText("");
        texUsuario.setText("");
        texContrasena.setText("");
        comboUsuario.setValue("");
    }
    /**
     * Registra acciones realizadas en la aplicación para auditoría o registro.
     * @param mensaje Descripción de la acción realizada.
     * @param nivel Nivel de importancia o tipo de acción.
     * @param accion La acción específica realizada.
     */
    private void registrarAcciones(String mensaje, int nivel, String accion) {
        UsuarioControllerService.registrarAcciones(mensaje, nivel, accion);
    }
    /**
     * Valida los datos de un UsuarioDto.
     * @param usuarioDto El objeto UsuarioDto a validar.
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean datosValidos(UsuarioDto usuarioDto) {
        StringBuilder mensaje = new StringBuilder();
        if (usuarioDto.nombre().isBlank())
            mensaje.append("El nombre es inválido.\n");
        if (usuarioDto.apellido().isBlank())
            mensaje.append("El apellido es inválido.\n");
        if (usuarioDto.cedula().isBlank())
            mensaje.append("La cédula es inválida.\n");
        if (usuarioDto.edad() == null || usuarioDto.edad() <= 0)
            mensaje.append("La edad es inválida.\n");
        if (usuarioDto.tipo().isBlank())
            mensaje.append("El tipo de usuario es inválido.\n");
        if (usuarioDto.nombreUsuario().isBlank())
            mensaje.append("El nombre de usuario es inválido.\n");
        if (usuarioDto.contrasenia().isBlank())
            mensaje.append("La contraseña es inválida.\n");

        if (mensaje.length() == 0) {
            return true;
        } else {
            mostrarMensaje("Notificación usuario", "Datos inválidos", mensaje.toString(), Alert.AlertType.WARNING);
            return false;
        }
    }
    /**
     * Muestra un mensaje emergente en la interfaz de usuario.
     * Utilizado para notificar al usuario sobre el resultado de ciertas acciones.
     * @param titulo El título del mensaje.
     * @param header El encabezado del mensaje.
     * @param contenido El contenido del mensaje.
     * @param alertType El tipo de alerta a mostrar.
     */
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    /**
     * Muestra un mensaje de confirmación y espera la respuesta del usuario.
     * @param mensaje El mensaje a mostrar en la confirmación.
     * @return true si el usuario confirma, false en caso contrario.
     */
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



