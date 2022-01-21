package com.unipi.datamining.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import static com.unipi.datamining.PersonalityClustering.*;

public class FXMLMusicStatsDocumentController implements Initializable{
    @FXML
    private BarChart musicBarChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicBarChart.setStyle("-fx-bar-fill: blue;");
        XYChart.Series<String, Number> values = new XYChart.Series<>();
        double[] musicValues = getAverageClusterMusicValues();
        // finire
        values.getData().add(new XYChart.Data<>("Danceability", musicValues[0]));
        values.getData().add(new XYChart.Data<>("Tempo", musicValues[1]));
        musicBarChart.getData().addAll(values);
    }
}
