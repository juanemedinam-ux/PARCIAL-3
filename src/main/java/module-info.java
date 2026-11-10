module co.edu.uniquindio.poo.parcial3 {

    requires javafx.controls;
    requires javafx.fxml;


    requires java.logging;


    opens co.edu.uniquindio.edu.poo.parcial3 to javafx.fxml;


    opens co.edu.uniquindio.edu.poo.parcial3.viewController to javafx.fxml;


    opens co.edu.uniquindio.edu.poo.parcial3.model to javafx.base;


    exports co.edu.uniquindio.edu.poo.parcial3;

    exports co.edu.uniquindio.edu.poo.parcial3.viewController;
}