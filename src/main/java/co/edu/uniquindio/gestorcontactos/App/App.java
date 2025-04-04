package co.edu.uniquindio.gestorcontactos.App;

import co.edu.uniquindio.gestorcontactos.Model.GestionContacto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        quemardatos();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GestionContactoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    void quemardatos() throws Exception {
        GestionContacto gestionContacto = GestionContacto.getInstancia();
        Image imgprueba = new Image((getClass().getResourceAsStream("/lupa.png")));
        gestionContacto.CrearContacto("Juan","Alvarez", "1234567890","juanito@gmail.com","23","12",imgprueba);
        gestionContacto.CrearContacto("Paco", "Sanchez", "0987654321","paco@gmail.com","12","1",imgprueba);
    }
}