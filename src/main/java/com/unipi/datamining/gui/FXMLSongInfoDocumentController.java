package com.unipi.datamining.gui;

import com.unipi.datamining.beans.SongBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLSongInfoDocumentController implements Initializable{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField name;
    @FXML
    private TextField album;
    @FXML
    private TextField artist;
    @FXML
    private TextField releaseDate;
    @FXML
    private BarChart featureBarChart;
    @FXML
    private ImageView image;
    public static SongBean selectedSong;

    @FXML
    private void showComments(ActionEvent event){
        featureBarChart.setStyle("-fx-bar-fill: blue;");
        System.out.println("Loading the stats page");
        LoaderFXML object = new LoaderFXML();
        Pane commentsPane = object.getPage("comments");
        try {
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(commentsPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        featureBarChart.setStyle("-fx-bar-fill: blue;");
        // song bar chart
        XYChart.Series<String, Number> values = new XYChart.Series<>();
        name.setText(selectedSong.getName());
        album.setText(selectedSong.getAlbum());
        artist.setText(selectedSong.getArtists()); //agisci su array
        image.setImage(selectedSong.getImage().getImage());
        releaseDate.setText(selectedSong.getReleaseDate());
        name.setEditable(false);
        album.setEditable(false);
        artist.setEditable(false);
        releaseDate.setEditable(false);
        //sotituisci con selectedSong.get() ecc
        values.getData().add(new XYChart.Data<>("Danceability", 2));
        values.getData().add(new XYChart.Data<>("Tempo", 3));
        //....
        featureBarChart.getData().addAll(values);
    }
}
