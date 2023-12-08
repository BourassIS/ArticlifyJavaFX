package com.ensa.srisearcher.controllers;

import com.ensa.srisearcher.models.SearchResultItem;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchItemController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text snippets;
    @FXML
    private Label bookAuthor;

    @FXML
    private VBox box;

    private String color ="FFFFFF";

    public void SetData(SearchResultItem searchResultItem){
        snippets.setText(searchResultItem.getSnippets());
        bookAuthor.setText(searchResultItem.getUrl());
    }

}
