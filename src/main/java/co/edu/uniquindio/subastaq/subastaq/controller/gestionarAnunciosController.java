package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.model.Anuncio;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import javafx.beans.property.SimpleObjectProperty;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class gestionarAnunciosController {

    @FXML
    private Button btnactualizarProducto;

    @FXML
    private Button btnagregarProducto;

    @FXML
    private Button btncargarFoto;

    @FXML
    private Button btneliminarProducto;

    @FXML
    private ComboBox<?> cbTipoProducto;

    @FXML
    private TableColumn<Anuncio, String> columnaNombreProducto;

    @FXML
    private TableColumn<Anuncio, TipoProducto> columnaTipoProducto;

    @FXML
    private TableColumn<Anuncio, LocalDate> columnaFechaPublicacion;

    @FXML
    private TableColumn<Anuncio, LocalDate> columnaFechaTerminacion;

    @FXML
    private TableView<Anuncio> tablaProductos;

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
    public void initialize() {
        // Configura las columnas con los nombres de las propiedades
        columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("producto.nombreProducto"));
        columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<>("producto.tipoProducto"));
        columnaFechaPublicacion.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(convertToLocalDateViaInstant(cellData.getValue().getFechaPublicacion())));
        columnaFechaTerminacion.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(convertToLocalDateViaInstant(cellData.getValue().getFechaLimite())));

    }
    // Método auxiliar para convertir Date a LocalDate
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

