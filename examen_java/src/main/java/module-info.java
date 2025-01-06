module com.example.examen_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen_java to javafx.fxml;
    exports com.example.examen_java;

    exports com.example.examen_java.controlleurs to javafx.fxml;  // Add this line
    opens com.example.examen_java.controlleurs to javafx.fxml;
}