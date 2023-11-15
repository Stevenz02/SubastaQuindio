package co.edu.uniquindio.subastaq.subastaq.controller;

import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.AnuncioDto;
import co.edu.uniquindio.subastaq.subastaq.mapping.dto.ProductoDto;
import co.edu.uniquindio.subastaq.subastaq.model.TipoProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class pujarProductoController {

    private ModelFactoryController modelFactoryController;

    @FXML
    private Button buttonOfertar;

    @FXML
    private TableColumn<ProductoDto, String> colmNombreProducto;

    @FXML
    private TableColumn<AnuncioDto, Double> colmPrecio;

    @FXML
    private TableColumn<ProductoDto, TipoProducto> colmTipoProducto;

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
    private TableView<?> tableAnuncios;

    @FXML
    private TextField textFieldPuja;

    @FXML
    void handleOfertar(ActionEvent event) {

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
}
