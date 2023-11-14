package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.aplicacion.aplicacion;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.AnuncioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncianteDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.ProductoDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class gestionarAnunciosController {

    private ModelFactoryController modelFactoryController;
    public void setModelFactoryController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
    }
    AnuncioController AnuncioControllerService;
    ObservableList<AnuncioDto> listaAnunciosDto = FXCollections.observableArrayList();
    AnuncioDto anuncioSeleccionado;
    UsuarioDto usuario = ModelFactoryController.getUsuarioActual();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnactualizarProducto;

    @FXML
    private Button btnagregarProducto;

    @FXML
    private Button btncargarFoto;

    @FXML
    private Button btneliminarProducto;

    @FXML
    private ComboBox<TipoProducto> cbTipoProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreAnunciante;

    @FXML
    private DatePicker dateFechaPublicacion;

    @FXML
    private DatePicker dateFechaFinalizacion;

    @FXML
    private TextField txtValorInicial;

    @FXML
    private ImageView imgFoto;

    @FXML
    private TableColumn<ProductoDto, String> columnaNombreProducto;

    @FXML
    private TableColumn<ProductoDto, TipoProducto> columnaTipoProducto;

    @FXML
    private TableColumn<AnuncioDto, LocalDate> columnaFechaPublicacion;

    @FXML
    private TableColumn<AnuncioDto, LocalDate> columnaFechaTerminacion;

    @FXML
    private TableView<AnuncioDto> tablaProductos;

    @FXML
    void bttcargarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        File file = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imgFoto.setImage(image);
        }
    }
    @FXML
    public void cerrarSesionAction(ActionEvent event) {
        if (modelFactoryController != null) {
            modelFactoryController.cerrarSesion(event);
        } else {
            // Manejar el caso en que modelFactoryController es null
            System.out.println("modelFactoryController es null");
        }
    }
    @FXML
    void bttactualizarProducto(ActionEvent event) {
    }
    @FXML
    void guardarAnuncioAction(ActionEvent event) {
    }
    @FXML
    void eliminarAnuncioAction(ActionEvent event) {
    }
    /*
    @FXML
    void initialize() {
        AnuncioControllerService = new AnuncioController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerAnuncios();
        tablaProductos.getItems().clear();
        tablaProductos.setItems(listaAnunciosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        // Configura las columnas con los nombres de las propiedades
        columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("producto.nombreProducto"));
        columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<>("producto.tipoProducto"));
        columnaFechaPublicacion.setCellValueFactory(cellData -> new SimpleObjectProperty<>(convertToLocalDateViaInstant(cellData.getValue().fechaPublicacion())));
        columnaFechaTerminacion.setCellValueFactory(cellData -> new SimpleObjectProperty<>(convertToLocalDateViaInstant(cellData.getValue().FechaLimite())));
    }

    // Método auxiliar para convertir Date a LocalDate
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private void obtenerAnuncios() {
        listaAnunciosDto.addAll(AnuncioControllerService.obtenerAnuncios());
    }

    private void listenerSelection() {
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            anuncioSeleccionado = newSelection;
            mostrarInformacionAnuncio(anuncioSeleccionado);
        });
    }

    private void mostrarInformacionAnuncio(AnuncioDto anuncioSeleccionado) {
        if (anuncioSeleccionado != null) {
            // Suponiendo que anuncioSeleccionado.productoDto() retorna un ProductoDto y tienes getters adecuados en ProductoDto.
            // También asumiendo que cbTipoProducto es un ComboBox y txtNombreProducto, txtDescripcion, y txtNombreAnunciante son TextFields.

            // Establecer el tipo de producto en el ComboBox, asegurándose de que el valor es el esperado y que TipoProducto es un enum.
            cbTipoProducto.setValue(anuncioSeleccionado.productoDto().tipoProducto());

            // Establecer el nombre del producto, la descripción y el nombre del anunciante en sus respectivos TextFields.
            txtNombreProducto.setText(anuncioSeleccionado.productoDto().nombreProducto());
            txtDescripcion.setText(anuncioSeleccionado.productoDto().descripcion());
            txtNombreAnunciante.setText(anuncioSeleccionado.anuncianteDto().nombre());

            // Convertir Date a LocalDate
            LocalDate fechaPubLocalDate = anuncioSeleccionado.fechaPublicacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFinLocalDate = anuncioSeleccionado.FechaLimite().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            dateFechaPublicacion.setValue(fechaPubLocalDate);
            dateFechaFinalizacion.setValue(fechaFinLocalDate);

            // Establecer el valor inicial en su TextField.
            txtValorInicial.setText(String.valueOf(anuncioSeleccionado.valorInicial()));

            // Para la imagen, se debe convertir la ruta de la imagen a un objeto Image y luego establecerla en un ImageView.
            // Suponiendo que tienes un getter que te da la URL o ruta de la imagen como un String.
            Image image = new Image(anuncioSeleccionado.productoDto().rutaImagen());
            imgFoto.setImage(image);
        }
    }



    private void crearAnuncio() {
        //1. Capturar los datos
        AnuncioDto anuncioDto = construirAnuncioDto();
        //2. Validar la información
        if (datosValidos(anuncioDto)) {
            if (AnuncioControllerService.agregarAnuncio(anuncioDto, usuario)) {
                listaAnunciosDto.add(anuncioDto);
                mostrarMensaje("Notificación Anuncio", "Anuncio creado", "El anuncio se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposAnuncio();
                registrarAcciones("Anuncio agregado", 1, "Anuncio Usuario");
            } else {
                mostrarMensaje("Notificación Anuncio", "Anuncio no creado", "El anuncio no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación Anuncio", "Anuncio no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private AnuncioDto construirAnuncioDto() {
        AnuncianteDto anuncianteDto = modelFactoryController.buscarAnuncianteNombre(txtNombreAnunciante.getText());
        ProductoDto productoDto = crearProductoDto(txtNombreProducto.getText(),cbTipoProducto.getValue(), txtDescripcion.getText(), );

        return new AnuncioDto(
                anuncianteDto,


        );
    }
    public ProductoDto crearProductoDto(String nombreProducto, TipoProducto tipoProducto, String descripcion, String rutaImagen){
        return new ProductoDto(nombreProducto, tipoProducto, descripcion, rutaImagen);
    }

    */

    private void limpiarCamposAnuncio() {
        cbTipoProducto.setValue(null);
        txtNombreProducto.clear();
        txtDescripcion.clear();
        txtNombreAnunciante.clear();
        dateFechaPublicacion.setValue(null);
        dateFechaFinalizacion.setValue(null);
        txtValorInicial.clear();
        imgFoto.setImage(null); // Si quieres limpiar la imagen también
    }
    private void registrarAcciones(String mensaje, int nivel, String accion) {
        AnuncioControllerService.registrarAcciones(mensaje, nivel, accion);
    }
    private boolean datosValidos(AnuncioDto anuncioDto) {
        StringBuilder mensaje = new StringBuilder();

        if (anuncioDto.anuncianteDto() == null || !datosUsuarioValidos(anuncioDto.anuncianteDto()))
            mensaje.append("Los datos del anunciante son inválidos.\n");

        if (anuncioDto.productoDto() == null || !datosProductoValidos(anuncioDto.productoDto()))
            mensaje.append("Los datos del producto son inválidos.\n");

        if (anuncioDto.fechaPublicacion() == null)
            mensaje.append("La fecha de publicación es inválida.\n");

        if (anuncioDto.FechaLimite() == null)
            mensaje.append("La fecha límite es inválida.\n");

        if (anuncioDto.valorInicial() == null || anuncioDto.valorInicial() <= 0)
            mensaje.append("El valor inicial es inválido.\n");

        if (anuncioDto.listaPujasDto() == null || anuncioDto.listaPujasDto().isEmpty())
            mensaje.append("La lista de pujas es inválida.\n");

        if (mensaje.length() == 0) {
            return true;
        } else {
            mostrarMensaje("Notificación de Anuncio", "Datos inválidos", mensaje.toString(), Alert.AlertType.WARNING);
            return false;
        }
    }
    private boolean datosUsuarioValidos(AnuncianteDto usuarioDto) {
        StringBuilder mensajeUsuario = new StringBuilder();

        if (usuarioDto.nombre().isBlank())
            mensajeUsuario.append("El nombre es inválido.\n");
        if (usuarioDto.apellido().isBlank())
            mensajeUsuario.append("El apellido es inválido.\n");
        if (usuarioDto.cedula().isBlank())
            mensajeUsuario.append("La cédula es inválida.\n");
        if (usuarioDto.edad() == null || usuarioDto.edad() <= 0)
            mensajeUsuario.append("La edad es inválida.\n");
        if (usuarioDto.tipo().isBlank())
            mensajeUsuario.append("El tipo de usuario es inválido.\n");
        if (usuarioDto.nombreUsuario().isBlank())
            mensajeUsuario.append("El nombre de usuario es inválido.\n");
        if (usuarioDto.contrasenia().isBlank())
            mensajeUsuario.append("La contraseña es inválida.\n");

        if (mensajeUsuario.length() == 0) {
            return true;
        } else {
            mostrarMensaje("Notificación usuario", "Datos inválidos", mensajeUsuario.toString(), Alert.AlertType.WARNING);
            return false;
        }
    }
    private boolean datosProductoValidos(ProductoDto productoDto) {
        StringBuilder mensajeProducto = new StringBuilder();
        if (productoDto.nombreProducto().isBlank())
            mensajeProducto.append("El nombre del producto es inválido.\n");
        if (productoDto.tipoProducto() == null)
            if (mensajeProducto.length() == 0) {
                return true;
            } else {
                mostrarMensaje("Notificación producto", "Datos inválidos", mensajeProducto.toString(), Alert.AlertType.WARNING);
                return false;
            }
        return false;
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

}

