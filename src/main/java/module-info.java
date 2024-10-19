module com.example.ejerciciod {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejerciciod to javafx.fxml;
    exports com.example.ejerciciod;
}