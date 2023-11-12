package co.edu.uniquindio.subastaq.subastaq.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class aplicacion extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage; // Almacenar el primaryStage
        mostrarVistaPrincipalLogin();
    }

    public void mostrarVistaPrincipalLogin() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalLogin.fxml"));
        primaryStage.setTitle("Inicio de sesion en Subastas Quindío");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void cambiarPanelAnunciante() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(aplicacion.class.getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalAnunciante.fxml"));
            Parent vistaPrincipalAnunciante = loader.load();

            // Establecer la nueva escena en el escenario principal
            primaryStage.setScene(new Scene(vistaPrincipalAnunciante));

            // Mostrar el escenario
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción más adecuadamente, mostrar un mensaje al usuario, etc.
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

