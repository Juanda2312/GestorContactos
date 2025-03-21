module co.edu.uniquindio.gestorcontactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens co.edu.uniquindio.gestorcontactos.App to javafx.fxml;
    exports co.edu.uniquindio.gestorcontactos.App;
    opens co.edu.uniquindio.gestorcontactos.Controller to javafx.fxml;
    exports co.edu.uniquindio.gestorcontactos.Controller;
}