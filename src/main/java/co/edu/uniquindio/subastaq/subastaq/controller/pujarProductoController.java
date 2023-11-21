package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.AnuncioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.exception.CompradorExepcion;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.ProductoDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.PujaDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import co.edu.uniquindio.subastaq.subastaq.model.Anuncio;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class pujarProductoController {

    ModelFactoryController modelFactoryController;
    AnuncioController AnuncioControllerService;
    ObservableList<AnuncioDto> listaAnunciosDto = FXCollections.observableArrayList();
    AnuncioDto anuncioSeleccionado;
    UsuarioDto usuarioDto = ModelFactoryController.getUsuarioActual();
    PujaDto pujaDto;
    public ProductoDto productoDto;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button buttonOfertar;

    @FXML
    private ImageView imgVistaProducto;

    @FXML
    private Label labelFechaFinal;

    @FXML
    private Label labelFechaInicial;

    @FXML
    private Label labelNombreProducto;

    @FXML
    private Label labelTipoProducto;

    @FXML
    private TextField textFieldPuja;

    @FXML
    private TableColumn<AnuncioDto, String> colmNombreAnunciante;

    @FXML
    private TableColumn<AnuncioDto, String> colmNombreProducto;

    @FXML
    private TableColumn<AnuncioDto, String> colmFechaLimite;

    @FXML
    private TableView<AnuncioDto> tableAnuncios;

    @FXML
    void ofertarAction(ActionEvent event) throws CompradorExepcion {
        pujaDto = new PujaDto(modelFactoryController.crearAleatorio(),new Date(), Double.parseDouble(textFieldPuja.getText()));
        if(modelFactoryController.agregarPuja(anuncioSeleccionado,pujaDto,usuarioDto)){
            mostrarMensaje("Notificación Puja", "Puja creada", "La puja se ha creado con éxito", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Notificación Puja", "Puja no creada", "La puja no se ha creado con éxito", Alert.AlertType.ERROR);
        }
    }
    public void cerrarSesionAction(ActionEvent event) {
        modelFactoryController = ModelFactoryController.getInstance();
        if (modelFactoryController != null) {
            modelFactoryController.cerrarSesion(event);
        } else {
            // Manejar el caso en que modelFactoryController es null
            System.out.println("modelFactoryController es null");
        }
    }
    @FXML
    void initialize() {
        AnuncioControllerService = new AnuncioController();
        modelFactoryController = ModelFactoryController.getInstance();
        intiView();
    }
    private void intiView() {
        initDataBinding();
        obtenerAnuncios();
        tableAnuncios.getItems().clear();
        tableAnuncios.setItems(listaAnunciosDto);
        listenerSelection();
    }
    private void initDataBinding() {
        // Configura las columnas con los nombres de las propiedades
        colmNombreAnunciante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().anuncianteDto().nombre()));
        colmNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoDto().nombreProducto()));
        colmFechaLimite.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().FechaLimite())));
    }
    private void obtenerAnuncios() {
        listaAnunciosDto.addAll(AnuncioControllerService.obtenerAnuncios());
    }
    private void listenerSelection() {
        tableAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            anuncioSeleccionado = newSelection;
        });
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
