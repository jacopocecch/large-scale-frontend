package com.unipi.largescale.gui;

import com.unipi.largescale.entities.aggregations.Album;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static com.unipi.largescale.PersonalityClustering.*;

public class FXMLMusicStatsDocumentController implements Initializable{
    @FXML
    private BarChart musicBarChart;
    @FXML
    private Label topAlbums;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicBarChart.setStyle("-fx-bar-fill: blue;");
        XYChart.Series<String, Number> values = new XYChart.Series<>();
        List<Album> albums = getClusterKHighestRatedAlbums();
        String text = "Most preferred album inside your cluster: ";
        for(int i = 0; i < albums.size();++i) {
            text+= albums.get(0).getId() + "\n";
        }
        topAlbums.setText(text);
        double[] musicValues = getAverageClusterMusicValues();
        // finire
        values.getData().add(new XYChart.Data<>("Danceability", musicValues[0]));
        values.getData().add(new XYChart.Data<>("Tempo", musicValues[1]));
        musicBarChart.getData().addAll(values);
    }
}
