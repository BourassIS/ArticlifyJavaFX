package com.ensa.srisearcher.controllers;

import com.ensa.srisearcher.models.SearchResultItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ArticleCardController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text snippets;
    @FXML
    private Label url;
    @FXML
    private Label bookAuthor;

    @FXML
    private VBox box;

    private String color ="FFFFFF";

    public void SetData(String url_){
        url.setText(url_);
    }

    @FXML
    void deleteArticleUrl(ActionEvent event) {
        /*
        * try{
            this.switchPage(event, "AddArticle.fxml");
        }catch (Exception e){
            e.printStackTrace();
        }
        * */
    }
}
