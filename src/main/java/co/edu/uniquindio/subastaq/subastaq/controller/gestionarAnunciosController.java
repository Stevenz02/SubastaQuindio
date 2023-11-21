package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.aplicacion.aplicacion;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.AnuncioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.*;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import co.edu.uniquindio.subastaq.subastaq.utils.Persistencia;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class gestionarAnunciosController {
    ModelFactoryController modelFactoryController;
    AnuncioController AnuncioControllerService;
    ObservableList<AnuncioDto> listaAnunciosDto = FXCollections.observableArrayList();
    AnuncioDto anuncioSeleccionado;
    UsuarioDto usuario = ModelFactoryController.getUsuarioActual();
    public ProductoDto productoDto;

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
    private Button btnExportarAnuncio;

    @FXML
    private ComboBox<TipoProducto> cbTipoProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private DatePicker dateFechaPublicacion;

    @FXML
    private DatePicker dateFechaFinalizacion;

    @FXML
    private TextField txtValorInicial;

    @FXML
    private ImageView imgFoto;

    @FXML
    private TableColumn<AnuncioDto, String> columnaNombreProducto;

    @FXML
    private TableColumn<AnuncioDto, String> columnaTipoProducto;

    @FXML
    private TableColumn<AnuncioDto, String> columnaFechaPublicacion;

    @FXML
    private TableColumn<AnuncioDto, String> columnaFechaTerminacion;

    @FXML
    private TableView<AnuncioDto> tablaProductos;

    public File fileSeleccionado;
    // Método para cargar una foto desde el sistema de archivos del usuario.
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
            this.fileSeleccionado = file;
            imgFoto.setImage(image);
        }
    }
    // Método para cerrar la sesión actual en la aplicación.
    @FXML
    public void cerrarSesionAction(ActionEvent event) {
        modelFactoryController = ModelFactoryController.getInstance();
        if (modelFactoryController != null) {
            modelFactoryController.cerrarSesion(event);
        } else {
            // Manejar el caso en que modelFactoryController es null
            System.out.println("modelFactoryController es null");
        }
    }
    // Método inicializador que se ejecuta al cargar la interfaz de usuario.
    @FXML
    void initialize() {
        AnuncioControllerService = new AnuncioController();
        modelFactoryController = ModelFactoryController.getInstance();
        cbTipoProducto.getItems().setAll(TipoProducto.values());
        intiView();
    }

    // Método auxiliar para inicializar la vista y cargar datos.
    private void intiView() {
        initDataBinding();
        obtenerAnuncios();
        tablaProductos.getItems().clear();
        tablaProductos.setItems(listaAnunciosDto);
        listenerSelection();
    }
// Configura el enlace de datos entre la interfaz de usuario y los modelos de datos.

    private void initDataBinding() {
        // Configura las columnas con los nombres de las propiedades
        columnaNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoDto().nombreProducto()));
        columnaTipoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().productoDto().tipoProducto())));
        columnaFechaPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fechaPublicacion())));
        columnaFechaTerminacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().FechaLimite())));
    }
// Obtiene y muestra anuncios desde el controlador de anuncios.
    private void obtenerAnuncios() {
        listaAnunciosDto.addAll(AnuncioControllerService.obtenerAnuncios());
    }
// Añade un listener a la selección de la tabla de productos.
    private void listenerSelection() {
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            anuncioSeleccionado = newSelection;
            mostrarInformacionAnuncio(anuncioSeleccionado);
        });
    }
// Muestra la información detallada de un anuncio seleccionado.
    private void mostrarInformacionAnuncio(AnuncioDto anuncioSeleccionado) {
        if (anuncioSeleccionado != null) {
            // Suponiendo que anuncioSeleccionado.productoDto() retorna un ProductoDto y tienes getters adecuados en ProductoDto.
            // También asumiendo que cbTipoProducto es un ComboBox y txtNombreProducto, txtDescripcion, y txtNombreAnunciante son TextFields.

            // Establecer el tipo de producto en el ComboBox, asegurándose de que el valor es el esperado y que TipoProducto es un enum.
            cbTipoProducto.setValue(anuncioSeleccionado.productoDto().tipoProducto());

            // Establecer el nombre del producto, la descripción y el nombre del anunciante en sus respectivos TextFields.
            txtNombreProducto.setText(anuncioSeleccionado.productoDto().nombreProducto());
            txtDescripcion.setText(anuncioSeleccionado.productoDto().descripcion());

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
    // Controlador del evento para actualizar un producto.
    @FXML
    void bttactualizarProducto(ActionEvent event) {
    }
    // Controlador del evento para guardar un nuevo anuncio.
    @FXML
    void guardarAnuncioAction(ActionEvent event) {
        crearAnuncio();
    }
    // Controlador del evento para eliminar un anuncio existente.
    @FXML
    void eliminarAnuncioAction(ActionEvent event) {
    }
// Crea un nuevo anuncio a partir de los datos de entrada del usuario.
    private void crearAnuncio() {
        //1. Capturar los datos
        AnuncioDto anuncioDto = construirAnuncioDto();
        if (anuncioDto == null) {
            mostrarMensaje("Error", "Anuncio no creado", "No se pudo crear el anuncio debido a datos inválidos.", Alert.AlertType.ERROR);
            return;
        }
        //2. Validar la información
        if (AnuncioControllerService.agregarAnuncio(anuncioDto, usuario)) {
            listaAnunciosDto.add(anuncioDto);
            mostrarMensaje("Notificación Anuncio", "Anuncio creado", "El anuncio se ha creado con éxito", Alert.AlertType.INFORMATION);
            limpiarCamposAnuncio();
            registrarAcciones("Anuncio agregado", 1, "Anuncio Usuario");
        } else {
            mostrarMensaje("Notificación Anuncio", "Anuncio no creado", "El anuncio no se ha creado con éxito", Alert.AlertType.ERROR);
        }
    }
// Construye un objeto AnuncioDto a partir de los datos de entrada del usuario.
    private AnuncioDto construirAnuncioDto() {
        AnuncianteDto anuncianteDto = modelFactoryController.buscarAnuncianteCedula(usuario.cedula());
        System.out.println("El anuncianteDto es: " + anuncianteDto);
        if (anuncianteDto == null) {
            // Si el anunciante no se encuentra, devuelve null para manejarlo adecuadamente.
            return null;
        }
        productoDto = crearProductoDto(txtNombreProducto.getText(), cbTipoProducto.getValue(), txtDescripcion.getText(), fileSeleccionado.getAbsolutePath());
        List<PujaDto> listaPujas = new ArrayList<>();

        return new AnuncioDto(
                anuncianteDto,
                productoDto,
                Date.from(dateFechaPublicacion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(dateFechaFinalizacion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Double.parseDouble(txtValorInicial.getText()),
                listaPujas
        );
    }
// Crea un objeto ProductoDto a partir de los datos de entrada del usuario.
    public ProductoDto crearProductoDto(String nombreProducto, TipoProducto tipoProducto, String descripcion, String rutaImagen){
        return new ProductoDto(nombreProducto, tipoProducto, descripcion, rutaImagen);
    }
    // Limpia los campos de entrada del formulario de anuncio.
    private void limpiarCamposAnuncio() {
        cbTipoProducto.setValue(null);
        txtNombreProducto.clear();
        txtDescripcion.clear();
        dateFechaPublicacion.setValue(null);
        dateFechaFinalizacion.setValue(null);
        txtValorInicial.clear();
        imgFoto.setImage(null); // Si quieres limpiar la imagen también
    }
    // Registra acciones realizadas en la aplicación para auditoría o registro.
    private void registrarAcciones(String mensaje, int nivel, String accion) {
        AnuncioControllerService.registrarAcciones(mensaje, nivel, accion);
    }
    // Valida los datos de un AnuncioDto.
    private boolean datosValidos(AnuncioDto anuncioDto) {
        StringBuilder mensaje = new StringBuilder();

        if (anuncioDto.anuncianteDto() == null)
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
    // Valida los datos de un ProductoDto.
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
    // Muestra un mensaje emergente en la interfaz de usuario.
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
// Controlador del evento para exportar anuncios a un archivo CSV.
    @FXML
    void exportarAnuncioAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setTitle("Guardar Datos");

        // Dialogo para guardar los Anunciantes
        File fileAnunciantes = fileChooser.showSaveDialog(stage);
        if (fileAnunciantes != null) {
            try {
                Persistencia.exportarAnunciantesCSV(modelFactoryController.getSubastaUniquindio().getListaAnunciantes(), fileAnunciantes);
                mostrarMensaje("Notificación Anuncio", "Anuncios exportados", "Los anuncios se ha exportado con éxito", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                mostrarMensaje("Notificación Anuncio", "Anuncios no exportados", "Los anuncios no se ha exportado con éxito", Alert.AlertType.ERROR);
            }
        }
    }
}

