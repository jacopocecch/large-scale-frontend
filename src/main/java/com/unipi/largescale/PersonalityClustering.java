package com.unipi.largescale;
import com.unipi.largescale.API.API;
import com.unipi.largescale.entities.*;
import com.unipi.largescale.entities.aggregations.Album;
import com.unipi.largescale.entities.aggregations.AverageMusicFeatures;
import com.unipi.largescale.entities.aggregations.Country;
import com.unipi.largescale.entities.aggregations.Id;
import com.unipi.largescale.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import static com.unipi.largescale.API.API.*;
import static com.unipi.largescale.util.UtilGUI.*;

public class PersonalityClustering extends Application {
    public static Stage stage;

    public void start(Stage stage){
        ConfigurationParameters configurationParameters = new ConfigurationParameters("src/main/resources/configurationParameters.xml");
        API.setConfiguration(configurationParameters);
        Properties props = System.getProperties();
        props.setProperty("javafx.platform", "Desktop");
        System.out.println("Loading the login page");
        FXMLLoader loader = new FXMLLoader(PersonalityClustering.class.getResource("/login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        PersonalityClustering.stage = stage;
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
