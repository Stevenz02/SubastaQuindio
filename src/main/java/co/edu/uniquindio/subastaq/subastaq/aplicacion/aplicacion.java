package co.edu.uniquindio.subastaq.subastaq.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class aplicacion extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/subastaq/subastaq/vistaPrincipalLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio de sesion en Subastas Quindío");
        primaryStage.show();
    }
    public static void main(String[] args) { launch(); }
}
