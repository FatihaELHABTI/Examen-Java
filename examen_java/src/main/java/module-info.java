module com.example.examen_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examen_java to javafx.fxml;
    exports com.example.examen_java;
}