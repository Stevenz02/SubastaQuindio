package co.edu.uniquindio.subastaq.subastaq.aplicacion;

import co.edu.uniquindio.subastaq.subastaq.controller.autenticarUsuarioController;
import co.edu.uniquindio.subastaq.subastaq.controllerModel.ModelFactoryController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class aplicacion extends Application {
    /**
     * Clase aplicacion para ejecutar las vistas.
     */
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Almacenar el primaryStage
        mostrarVistaPrincipalLogin();
    }

    public void mostrarVistaPrincipalLogin() {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalLogin.fxml"));
            Parent root = loader.load();

            // Aquí obtienes el controlador correcto de la vista que acabas de cargar
            autenticarUsuarioController controller = loader.getController();

            // Suponiendo que 'ModelFactoryController' tiene un método para establecer la instancia de 'aplicacion'
            ModelFactoryController.getInstance().setAplicacion(this);

            // Configurar la escena y mostrarla
            primaryStage.setTitle("Inicio de sesión en Subastas Quindío");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cambiarPanelAnunciante() {
        try {
            // Cargar la vista del anunciante
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalAnunciante.fxml"));
            Parent vistaPrincipalAnunciante = loader.load();

            // Establecer la nueva escena en el escenario principal
            primaryStage.setScene(new Scene(vistaPrincipalAnunciante));

            // Mostrar el escenario con la nueva vista
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cambiarPanelComprador() {
        try {
            // Cargar la vista del comprador
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalComprador.fxml"));
            Parent vistaPrincipalComprador = loader.load();

            // Establecer la nueva escena en el escenario principal
            primaryStage.setScene(new Scene(vistaPrincipalComprador));

            // Mostrar el escenario con la nueva vista
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para cambiar a la vista de inicio de sesión.
     */
    public void volverALogin() {
        mostrarVistaPrincipalLogin();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

