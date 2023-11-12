package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.AnuncioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.UsuarioController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.ProductoDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.UsuarioDto;
import co.edu.uniquindio.subastaq.subastaq.model.Anuncio;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class gestionarAnunciosController {

    AnuncioController AnuncioControllerService;
    ObservableList<AnuncioDto> listaAnunciosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

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
    private DatePicker dateFechaFinalizacion;

    @FXML
    private DatePicker dateFechaPublicacion;

    @FXML
    private ImageView imgFoto;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreAnunciante;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtValorInicial;

    @FXML
    void bttactualizarProducto(ActionEvent event) {

    }

    @FXML
    void bttagregarProducto(ActionEvent event) {

    }

    @FXML
    void bttcargarFoto(ActionEvent event) {

    }

    @FXML
    void btteliminarProducto(ActionEvent event) {

    }

    @FXML
    void initialize() {
        AnuncioControllerService = new AnuncioController();
        intiView();
    }
    private void intiView() {
        initDataBinding();
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
}

