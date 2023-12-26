module com.example.libraryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;
    requires lombok;
    requires mysql.connector.j;
    requires org.slf4j;
    requires ch.qos.logback.classic;

    opens com.ensa.srisearcher to javafx.fxml;
    exports com.ensa.srisearcher;
    exports com.ensa.srisearcher.controllers;
    exports com.ensa.srisearcher.models;

    opens com.ensa.srisearcher.controllers to javafx.fxml;


}