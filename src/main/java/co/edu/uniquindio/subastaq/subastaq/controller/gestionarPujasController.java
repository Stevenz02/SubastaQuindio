package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.AnuncioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.ProductoDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.PujaDto;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
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
    AnuncioController AnuncioControllerService;
    ObservableList<AnuncioDto> listaAnunciosDto = FXCollections.observableArrayList();
    AnuncioDto anuncioSeleccionado;
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
    private TableColumn<PujaDto, Double> columnOferta;

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
        AnuncioControllerService = new AnuncioController();
        modelFactoryController = ModelFactoryController.getInstance();
        intiView();
    }
    private void intiView() {
        initDataBinding();
        obtenerAnuncios();
        tableAnuncios.getItems().clear();
        tableAnuncios.setItems(listaAnunciosDto);
        tablePujas.getItems().clear();
        //tablePujas.setItems(listaPujasDto);
        listenerSelection();
    }
    private void initDataBinding() {
        // Configura las columnas con los nombres de las propiedades
        colmNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoDto().nombreProducto()));
        colmFechaPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fechaPublicacion())));
        colmFechaFinalizacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().FechaLimite())));

    }
    private void obtenerAnuncios() {
        listaAnunciosDto.addAll(AnuncioControllerService.obtenerAnuncios());
    }
/*
    private void obtenerPujas() {
        listaPujasDto.addAll(AnuncioControllerService.obtenerPujas());
    }

 */
    private void listenerSelection() {
        tableAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            anuncioSeleccionado = newSelection;
        });
    }
}

