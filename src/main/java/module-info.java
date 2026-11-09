
module co.edu.uniquindio.poo.parcial3 {

    requires javafx.controls;
    requires javafx.fxml;

    // Permitir que JavaFX acceda a la clase principal
    opens co.edu.uniquindio.edu.poo.parcial3 to javafx.fxml;

    // Permitir que JavaFX encuentre los controladores
    opens co.edu.uniquindio.edu.poo.parcial3.viewController to javafx.fxml;

    // Permitir que JavaFX acceda a las propiedades del modelo
    opens co.edu.uniquindio.edu.poo.parcial3.model to javafx.base;

    // Exportar los paquetes principales
    exports co.edu.uniquindio.edu.poo.parcial3;
    exports co.edu.uniquindio.edu.poo.parcial3.viewController;
}
