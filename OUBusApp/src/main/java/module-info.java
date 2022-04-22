module com.mycompany.oubusapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.oubusapp to javafx.fxml;
    exports com.mycompany.oubusapp;
}
