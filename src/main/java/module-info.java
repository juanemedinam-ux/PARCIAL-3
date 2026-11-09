module co.edu.uniquindio.edu.poo.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.edu.poo.parcial3 to javafx.fxml;
    exports co.edu.uniquindio.edu.poo.parcial3;
    exports co.edu.uniquindio.edu.poo.parcial3.model;
    opens co.edu.uniquindio.edu.poo.parcial3.model to javafx.fxml;
    exports;
    opens to
}