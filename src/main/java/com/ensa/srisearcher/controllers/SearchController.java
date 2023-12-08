package com.ensa.srisearcher.controllers;

import com.ensa.srisearcher.Main;
import com.ensa.srisearcher.algorithms.DataStore;
import com.ensa.srisearcher.algorithms.InvertedIndex;
import com.ensa.srisearcher.models.SearchResultItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class SearchController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button startNowButton;
    @FXML
    private TextField query;
    @FXML
    protected VBox BookLayout;
    @FXML
    private ImageView goToSearchPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<SearchResultItem> results= new ArrayList<>();
        try {
            for(int i =0 ;i<results.size(); i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/ensa/srisearcher/views/search-item.fxml"));
                VBox cardboard =fxmlLoader.load();
                SearchItemController cardBookController = fxmlLoader.getController();
                cardBookController.SetData(results.get(i));
                BookLayout.getChildren().add(cardboard);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void searchArticles(ActionEvent event) {
        if(query.getText().isEmpty() || query.getText().isBlank())return;
        Set<Integer> indexes=DataStore.invertedIndex.search(query.getText());
        BookLayout.getChildren().clear();
        try {
            for (Integer i: indexes){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/ensa/srisearcher/views/search-item.fxml"));
                VBox cardboard =fxmlLoader.load();
                SearchItemController cardBookController = fxmlLoader.getController();
                SearchResultItem searchResultItem = new SearchResultItem(
                        DataStore.mapsDocIdsToUrls.get(i),
                        "Testing"
                );
                cardBookController.SetData(searchResultItem);
                BookLayout.getChildren().add(cardboard);
            }
        }catch (IOException e){
            e.printStackTrace();

        }
        // Update the vbox
    }

    public  void switchPage(ActionEvent event, String pageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/ensa/srisearcher/views/"+pageName));
        System.out.println("FXML Loader URL: " + Main.class.getResource("/com/ensa/srisearcher/views/" + pageName));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setWidth(1034);
        stage.setHeight(800);
        stage.show();
    }
}
