package com.ensa.srisearcher.controllers;

import com.ensa.srisearcher.Main;
import com.ensa.srisearcher.algorithms.DataStore;
import com.ensa.srisearcher.models.SearchResultItem;
import com.ensa.srisearcher.utils.Converter;
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
import javafx.scene.input.MouseEvent;
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
        DataStore dataStore = Converter.getDataStore();
        if(query.getText().isEmpty() || query.getText().isBlank())return;
        Set<Integer> docIds=Converter.search(query.getText());
        System.out.println(docIds);
        BookLayout.getChildren().clear();
        try {
            for (Integer docId: docIds){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/ensa/srisearcher/views/search-item.fxml"));
                VBox cardboard =fxmlLoader.load();
                SearchItemController cardBookController = fxmlLoader.getController();
                SearchResultItem searchResultItem = new SearchResultItem(
                        dataStore.mapsDocIdsToUrls.get(docId),
                        DataStore.getSnippetFromUrl(dataStore.mapsDocIdsToUrls.get(docId))
                );
                cardBookController.SetData(searchResultItem);
                BookLayout.getChildren().add(cardboard);
            }
        }catch (IOException e){
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

    @FXML
    public void goToAddArticlePageCall(MouseEvent event){
        try {
            this.switchPageMouse(event, "AddArticle.fxml");
        }catch (IOException e){
            e.printStackTrace();


        }
    }
}
