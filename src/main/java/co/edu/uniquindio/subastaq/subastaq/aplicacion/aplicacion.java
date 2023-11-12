package co.edu.uniquindio.subastaq.subastaq.aplicacion;

import co.edu.uniquindio.subastaq.subastaq.controller.registrarUsuarioController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class aplicacion extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio de sesion en Subastas Quindío");
        primaryStage.show();
    }

    public void cambiarPanelAnunciante(ActionEvent event) {
        try {
            // Cargar el archivo FXML para la vista principal del anunciante
            Parent vistaPrincipalAnunciante = FXMLLoader.load(getClass().getResource("co/edu/uniquindio/subastaq/subastaq/vistaPrincipalAnunciante.fxml"));
            // Obtener el escenario a través del evento que desencadenó el cambio
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            // Establecer la nueva escena en el escenario actual
            stage.setScene(new Scene(vistaPrincipalAnunciante));
            // Mostrar el escenario
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de error (puedes mostrar un mensaje de error al usuario si es necesario)
        }
    }
    public static void main(String[] args) { launch(); }
}
