package com.ensa.srisearcher.controllers;

import com.ensa.srisearcher.models.SearchResultItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class SearchItemController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text snippets;
    @FXML
    private Label urlLink;

    @FXML
    private VBox box;

    private String color ="FFFFFF";

    public void SetData(SearchResultItem searchResultItem){
        snippets.setText(searchResultItem.getSnippets());
        urlLink.setText(searchResultItem.getUrl());
    }


    @FXML
    void openArticleLink(MouseEvent event) {
        System.out.println("Open article link");
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(urlLink.getText()));
            } else {
                openLinkInFallbackBrowser(urlLink.getText());
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void openLinkInFallbackBrowser(String url) {
        // Implement a fallback method to open the link using another approach
        try {
            // Launch the default web browser with the specified URL
            Runtime.getRuntime().exec("xdg-open " + url);
            // Note: xdg-open is a Linux-specific command to open a URL in the default web browser
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
