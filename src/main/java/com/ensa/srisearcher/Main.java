package com.ensa.srisearcher;

import com.ensa.srisearcher.utils.Converter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ensa/srisearcher/views/root.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 973, 680);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/com/ensa/srisearcher/views/icon.png")));
        stage.setTitle("Articlify");
        stage.setScene(scene);
        stage.setWidth(973);
        stage.setHeight(680);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}