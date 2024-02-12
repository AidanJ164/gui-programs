module justice_aidan.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens justice_aidan.catcafe to javafx.fxml;
    exports justice_aidan.catcafe;
}