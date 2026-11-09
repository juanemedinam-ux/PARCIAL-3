
package co.edu.uniquindio.edu.poo.parcial3;

import co.edu.uniquindio.edu.poo.parcial3.model.*;
import co.edu.uniquindio.edu.poo.parcial3.viewController.PanaderiaViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static Panaderia panaderia = new Panaderia("El Trigal");

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("Panadería El Trigal");

        // Empleados iniciales
        panaderia.agregarEmpleado(
                new PanaderoArtesanal("Ana", "111", "A01", 100)
        );

        panaderia.agregarEmpleado(
                new PanaderoIndustrial("Carlos", "222", "I01", 120)
        );

        panaderia.agregarEmpleado(
                new Cajero("Lucia", "333", "C01", 480, "Caja 1")
        );

        // Cargar la vista FXML
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(
                App.class.getResource(
                        "/co/edu/uniquindio/edu/poo/parcial3/panaderiaView.fxml"
                )
        );

        AnchorPane rootLayout = loader.load();

        // Obtener el controlador
        PanaderiaViewController controller = loader.getController();

        controller.setApp(this);

        // Crear la escena
        Scene scene = new Scene(rootLayout);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
