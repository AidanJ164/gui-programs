module justice_aidan.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens justice_aidan.javafx to javafx.fxml;
    exports justice_aidan.javafx;
}