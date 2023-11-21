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

    @FXML
    void initialize() {
        anuncioControllerService = new AnuncioController();
        modelFactoryController = ModelFactoryController.getInstance();
        listaAnunciosDto = FXCollections.observableArrayList();
        listaPujasDto = FXCollections.observableArrayList();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerAnuncios();
        tableAnuncios.setItems(listaAnunciosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colmNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoDto().nombreProducto()));
        colmFechaPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fechaPublicacion())));
        colmFechaFinalizacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().FechaLimite())));

        columnCodigoPuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        columnFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fecha())));
        columnOferta.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().ofertaActual())));
    }

    private void obtenerAnuncios() {
        // Aquí debes reemplazar con tu método para obtener los anuncios
        listaAnunciosDto.addAll(anuncioControllerService.obtenerAnuncios());
    }

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

    private void listenerSelection() {
        tableAnuncios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                anuncioSeleccionado = newValue;
                obtenerPujas(); // Actualiza la tabla de pujas basado en el anuncio seleccionado.
            }
        });
    }
}

