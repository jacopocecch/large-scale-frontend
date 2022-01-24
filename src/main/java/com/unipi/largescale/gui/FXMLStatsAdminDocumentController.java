package com.unipi.largescale.gui;

import com.unipi.largescale.beans.SongBean;
import com.unipi.largescale.entities.Song;
import com.unipi.largescale.entities.aggregations.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Spliterator;

import static com.unipi.largescale.service.AdminService.*;
import static com.unipi.largescale.service.UserService.*;




public class FXMLStatsAdminDocumentController implements Initializable{
    @FXML
    private Label result;
    @FXML
    private BarChart barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private void showClusterHighestVariance(ActionEvent event){
        int cluster = getClusterHighestVariance();
        result.setText("The cluster with the highest variance is " + cluster);
        updateClusterBarChart(cluster);
    }

    @FXML
    private void showMostDanceableCluster(ActionEvent event){
        int cluster = getMostDanceableCluster();
        result.setText("The most danceable cluster is " + cluster);
        updateClusterBarChart(cluster);
    }

    @FXML
    private void showTopKStrongestCountries(ActionEvent event){
        List<Country> list = getTopKCountries();
        String text = "The top " + list.size()+ " of the strongest countries are the following:\n";
        for(int i = 0; i < list.size(); ++i) {
           text += i+1 + ")" + list.get(i).getId() + "\n";
        }
        result.setText(text);
        updateCountryBarChart(list);
    }

    private void updateCountryBarChart(List<Country> list){
        barChart.getData().clear();
        barChart.setStyle("-fx-bar-fill: blue;");
        XYChart.Series<String, Number> values = new XYChart.Series<>();
        for(int i = 0; i < list.size(); ++i)
            values.getData().add(new XYChart.Data<>(list.get(i).getId(), list.get(i).getAvg()));
        barChart.getData().addAll(values);
        barChart.getData().clear();
        barChart.setStyle("-fx-bar-fill: blue;");
        values = new XYChart.Series<>();
        for(int i = 0; i < list.size(); ++i)
            values.getData().add(new XYChart.Data<>(list.get(i).getId(), list.get(i).getAvg()));
        barChart.getData().addAll(values);
    }

    private void updateClusterBarChart(int cluster){
        barChart.getData().clear();
        barChart.setStyle("-fx-bar-fill: blue;");
        XYChart.Series<String, Number> values = new XYChart.Series<>();
        double[] personalityValues = getClusterPersonalityValues(cluster);
        values.getData().add(new XYChart.Data<>("OPN", personalityValues[0]));
        values.getData().add(new XYChart.Data<>("AGR", personalityValues[1]));
        values.getData().add(new XYChart.Data<>("NSM", personalityValues[2]));
        values.getData().add(new XYChart.Data<>("EXT", personalityValues[3]));
        values.getData().add(new XYChart.Data<>("CNS", personalityValues[4]));
        values.getData().add(new XYChart.Data<>("Time", personalityValues[5]));
        barChart.getData().addAll(values);
        barChart.getData().clear();
        barChart.setStyle("-fx-bar-fill: blue;");
        values = new XYChart.Series<>();
        personalityValues = getClusterPersonalityValues(cluster);
        values.getData().add(new XYChart.Data<>("OPN", personalityValues[0]));
        values.getData().add(new XYChart.Data<>("AGR", personalityValues[1]));
        values.getData().add(new XYChart.Data<>("NSM", personalityValues[2]));
        values.getData().add(new XYChart.Data<>("EXT", personalityValues[3]));
        values.getData().add(new XYChart.Data<>("CNS", personalityValues[4]));
        values.getData().add(new XYChart.Data<>("Time", personalityValues[5]));
        barChart.getData().addAll(values);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
    }
}
