module edu.westga.cs1302.Lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens edu.westga.cs1302.Lab3.views to javafx.fxml;
    exports edu.westga.cs1302.Lab3;
}
