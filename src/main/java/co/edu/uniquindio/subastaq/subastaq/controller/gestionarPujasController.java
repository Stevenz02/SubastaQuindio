package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.AnuncioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.ProductoDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.PujaDto;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class gestionarPujasController {

    ModelFactoryController modelFactoryController;
    AnuncioController anuncioControllerService;
    ObservableList<AnuncioDto> listaAnunciosDto = FXCollections.observableArrayList();
    ObservableList<PujaDto> listaPujasDto = FXCollections.observableArrayList();
    AnuncioDto anuncioSeleccionado;
    PujaDto pujaSeleccionado;
    public ProductoDto productoDto;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonElegirPuja;

    @FXML
    private TableColumn<PujaDto, String> columnCodigoPuja;

    @FXML
    private TableColumn<PujaDto, String> columnFecha;

    @FXML
    private TableColumn<PujaDto, String> columnOferta;

    @FXML
    private TableView<PujaDto> tablePujas;

    @FXML
    private TableColumn<AnuncioDto, String> colmNombreProducto;

    @FXML
    private TableColumn<AnuncioDto, String> colmFechaPublicacion;

    @FXML
    private TableColumn<AnuncioDto, String> colmFechaFinalizacion;

    @FXML
    private TableView<AnuncioDto> tableAnuncios;

    // Método inicializador que se ejecuta al cargar la interfaz de usuario.
    @FXML
    void initialize() {
        anuncioControllerService = new AnuncioController();
        modelFactoryController = ModelFactoryController.getInstance();
        listaAnunciosDto = FXCollections.observableArrayList();
        listaPujasDto = FXCollections.observableArrayList();
        initView();
    }
    /**
     * Inicializa la vista principal de la aplicación.
     * Configura la vinculación de datos y carga los anuncios para mostrar en la tabla.
     */
    private void initView() {
        initDataBinding();
        obtenerAnuncios();
        tableAnuncios.setItems(listaAnunciosDto);
        listenerSelection();
    }
    /**
     * Configura el enlace de datos entre la interfaz de usuario y los modelos de datos.
     * Establece cómo se mostrarán los datos en las columnas de la tabla.
     */
    private void initDataBinding() {
        colmNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoDto().nombreProducto()));
        colmFechaPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fechaPublicacion())));
        colmFechaFinalizacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().FechaLimite())));

        columnCodigoPuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        columnFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fecha())));
        columnOferta.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().ofertaActual())));
    }
    /**
     * Obtiene y agrega los anuncios al modelo de datos observable para mostrar en la interfaz.
     * Debe reemplazarse con el método específico de obtención de anuncios.
     */
    private void obtenerAnuncios() {
        // Aquí debes reemplazar con tu método para obtener los anuncios
        listaAnunciosDto.addAll(anuncioControllerService.obtenerAnuncios());
    }
    /**
     * Obtiene y muestra las pujas para el anuncio seleccionado actualmente.
     * Limpia y actualiza la lista de pujas en la interfaz de usuario.
     */
    private void obtenerPujas() {
        if (anuncioSeleccionado != null) {
            listaPujasDto.clear();
            listaPujasDto.addAll(anuncioControllerService.obtenerPujas(anuncioSeleccionado));
            Platform.runLater(() -> {
                tablePujas.setItems(listaPujasDto);
                tablePujas.refresh();
            });
        }
    }
    /**
     * Añade un listener a la selección en la tabla de anuncios.
     * Actualiza la información mostrada en la interfaz de usuario basada en el anuncio seleccionado.
     */
    private void listenerSelection() {
        tableAnuncios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                anuncioSeleccionado = newValue;
                obtenerPujas(); // Actualiza la tabla de pujas basado en el anuncio seleccionado.
            }
        });
    }
}

