package com.ensa.srisearcher.controllers;

import com.ensa.srisearcher.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchController {


    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button startNowButton;

    @FXML
    private ImageView goToSearchPage;

    @FXML
    void searchArticles(ActionEvent event) {
        System.out.println("Searching articles");
    }

    @FXML
    void goToAddArticlePageCall(MouseEvent event) {
        System.out.println("Going to the AddArticle page");
        try{
            this.switchPageMouse(event, "AddArticle.fxml");
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public  void switchPage(ActionEvent event, String pageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/ensa/srisearcher/views/"+pageName));
        System.out.println("FXML Loader URL: " + Main.class.getResource("/com/ensa/srisearcher/views/" + pageName));
        Scene scene = new Scene(fxmlLoader.load(), 960, 640);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setWidth(960);
        stage.setHeight(640);
        stage.show();
    }

    public  void switchPageMouse(MouseEvent event, String pageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/ensa/srisearcher/views/"+pageName));
        System.out.println("FXML Loader URL: " + Main.class.getResource("/com/ensa/srisearcher/views/" + pageName));
        Scene scene = new Scene(fxmlLoader.load(), 973, 680);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setWidth(973);
        stage.setHeight(680);
        stage.show();
    }
}
