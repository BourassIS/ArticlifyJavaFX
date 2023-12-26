package com.ensa.srisearcher.controllers;
import com.ensa.srisearcher.Main;
import com.ensa.srisearcher.algorithms.DataStore;
import com.ensa.srisearcher.algorithms.ScraperAlgo;
import com.ensa.srisearcher.utils.Converter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class AddArticleController implements Initializable {
    private final ScraperAlgo scraperAlgo=new ScraperAlgo();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public VBox articlesList;

    @FXML
    private Button startNowButton;

    @FXML
    private TextField articleUrl;

    @FXML
    private ImageView goToSearchPageButton;



    @FXML
    void addArticleUrl(MouseEvent event) {
        DataStore dataStore= Converter.getDataStore();
        if(articleUrl.getText().isEmpty() || articleUrl.getText().isBlank())return;
        articlesList.getChildren().clear();
        dataStore.urls.add(articleUrl.getText());
        ArrayList<String> urls= new ArrayList<>(dataStore.urls);
        try {
            for(int i =0 ;i<urls.size();i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/ensa/srisearcher/views/ArticleCard.fxml"));
                VBox cardboard =fxmlLoader.load();
                ArticleCardController cardBookController = fxmlLoader.getController();
                cardBookController.SetData(urls.get(i));
                articlesList.getChildren().add(cardboard);
            }
        }catch (IOException e){
            e.printStackTrace();

        }
        Converter.update(dataStore);
        scraperAlgo.pageScraper(articleUrl.getText());
    }

    @FXML
    void goToSearchPageCall(MouseEvent event) {

        try{
            this.switchPageMouse(event, "search.fxml");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataStore dataStore = Converter.getDataStore();
        System.out.println(dataStore.urls);
        ArrayList<String> urls= new ArrayList<>(dataStore.urls);
        try {
            for(int i =0 ;i<urls.size();i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/ensa/srisearcher/views/ArticleCard.fxml"));
                VBox cardboard =fxmlLoader.load();
                ArticleCardController cardBookController = fxmlLoader.getController();
                cardBookController.SetData(urls.get(i));
                articlesList.getChildren().add(cardboard);
            }
        }catch (IOException e){
            e.printStackTrace();

        }

    }

    public  void switchPage(ActionEvent event, String pageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/ensa/srisearcher/views/" + pageName));
        System.out.println("FXML Loader URL: " + Main.class.getResource("/com/ensa/srisearcher/views/" + pageName));
        Scene scene = new Scene(fxmlLoader.load(), 973, 680);
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
