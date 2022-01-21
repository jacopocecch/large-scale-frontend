package com.unipi.datamining.gui;

import com.unipi.datamining.beans.SongBean;
import com.unipi.datamining.entities.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.unipi.datamining.PersonalityClustering.getMainCluster;


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
    private TextField yearOfRelease;
    @FXML
    private BarChart featureBarChart;
    @FXML
    private Label mainCluster;
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
        yearOfRelease.setText(String.valueOf(selectedSong.getYear()));
        name.setEditable(false);
        album.setEditable(false);
        artist.setEditable(false);
        yearOfRelease.setEditable(false);
        values.getData().add(new XYChart.Data<>("Danceability", selectedSong.getDanceability()));
        values.getData().add(new XYChart.Data<>("Acousticness", selectedSong.getAcousticness()));
        values.getData().add(new XYChart.Data<>("Energy", selectedSong.getEnergy()));
        values.getData().add(new XYChart.Data<>("Instrumentalness", selectedSong.getInstrumentalness()));
        values.getData().add(new XYChart.Data<>("Liveness", selectedSong.getLiveness()));
        values.getData().add(new XYChart.Data<>("Loudness", selectedSong.getLoudness()));
        values.getData().add(new XYChart.Data<>("Valence", selectedSong.getValence()));
        featureBarChart.getData().addAll(values);
        mainCluster.setText(getMainCluster(new Song(selectedSong)));
    }
}
