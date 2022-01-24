package com.unipi.largescale;
import com.unipi.largescale.API.API;
import com.unipi.largescale.entities.*;
import com.unipi.largescale.entities.aggregations.Album;
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
    public static User user;
    public static double timeStart;
    private static final int[] answers = new int[50];
    private static final double[] times = new double[50];

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

    // method called each time a new question is answered;
    public static void saveAnswer(int index, int answer, double time) throws Exception {
        if(index < 50){
            answers[index] = answer;
            times[index] = time;
        }
        // last question
        if(index == 49){
            // flipping questions negatively correlated
            for(int i = 0; i < 50; i++){
                if(isNegativelyCorrelated(i+1)) {
                    switch(answers[i]) {
                        case 1 -> answers[i] = 5;
                        case 2 -> answers[i] = 4;
                        case 4 -> answers[i] = 2;
                        case 5 -> answers[i] = 1;
                    }
                }
            }
            double value = 0;
            double cumulativeTime = 0;
            for(int i = 0; i < 10; ++i){
               value += answers[i];
               cumulativeTime += times[i];
            }
            user.setExtraversion(value/10);
            value = 0;
            for(int i = 10; i < 20; ++i){
                value += answers[i];
                cumulativeTime += times[i];
            }
            user.setNeuroticism(value/10);
            value = 0;
            for(int i = 20; i < 30; ++i){
                value += answers[i];
                cumulativeTime += times[i];
            }
            user.setAgreeableness(value/10);
            value = 0;
            for(int i = 30; i < 40; ++i){
                value += answers[i];
                cumulativeTime += times[i];
            }
            user.setConscientiousness(value/10);
            value = 0;
            for(int i = 40; i < 50; ++i){
                value += answers[i];
                cumulativeTime += times[i];
            }
            user.setOpenness(value/10);
            user.setTimeSpent(cumulativeTime/60);
            System.out.println("Sending answers to the server");
            user = API.registerUser(user);
        }
    }

    public static int getClusterID(){
        return user.getCluster();
    }

    public static void logoutUser(){
        user = null;
    }

    public static void likeSong(Song song){
        updatePreferenceSong(user, song, +1);
    }

    public static void unlikeSong(Song song){
        updatePreferenceSong(user, song, -1);
    }

    public static List<Comment> showAllComments(Song song){
        return API.getSongComments(song);
    }

    public static List<Comment> showComments(Song song){
        return song.getComments();
    }

    public static Comment commentSong(String text, Song song){
        return API.commentSong(song, new Comment(user.getId(), song.getId(), user.getFirstName(),user.getLastName(), text));
    }

    public static double[] getAverageClusterMusicValues(){
        // to do
        return new double[]{1,2,3,4,5,6};
    }

    public static int getClusterHighestVariance(){
        return API.getClusterHighestVariance();
    }

    public static int getMostDanceableCluster(){
        return API.getMostDanceableCluster().getId();
    }


    public static List<Album> getClusterKHighestRatedAlbums(){
        List<Album> list = API.getClusterKHighestRatedAlbums(user.getCluster(), 1);
        System.out.println(list.size());
        return list;
    }

    public static List<Country> getTopKCountries(){
        return API.getTopKCountries(3);
    }


    public static void updateUserInfo(User newUser){
        newUser.setAgreeableness(user.getAgreeableness());
        newUser.setNeuroticism(user.getNeuroticism());
        newUser.setConscientiousness(user.getConscientiousness());
        newUser.setOpenness(user.getOpenness());
        newUser.setExtraversion(user.getExtraversion());
        newUser.setTimeSpent(user.getTimeSpent());
        newUser.setCluster(user.getCluster());
        newUser.setId(user.getId());
        API.updateUserInfo(newUser);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setFullName(newUser.getFullName());
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setCountry(newUser.getCountry());
        user.setGender(newUser.getGender());
        user.setDateOfBirth(newUser.getDateOfBirth());
        user.setImage(newUser.getImage());
    }

    public static double[] getClusterPersonalityValues(){
        ClusterValues clusterValues = API.getClusterValues(user.getCluster());
        if(clusterValues != null)
            return new double[]{clusterValues.getOpenness(),clusterValues.getAgreeableness(),clusterValues.getNeuroticism(),clusterValues.getExtraversion(),clusterValues.getConscientiousness(),clusterValues.getTimeSpent()};
        else return null;
    }

    public static double[] getClusterPersonalityValues(int cluster){
        ClusterValues clusterValues = API.getClusterValues(cluster);
        if(clusterValues != null)
            return new double[]{clusterValues.getOpenness(),clusterValues.getAgreeableness(),clusterValues.getNeuroticism(),clusterValues.getExtraversion(),clusterValues.getConscientiousness(),clusterValues.getTimeSpent()};
        else return null;
    }

    public static String getDeviation(){
        ClusterValues clusterValues= API.getClusterValues(user.getCluster());
        if(clusterValues != null) {
            double diffC = Math.abs(user.getConscientiousness() - clusterValues.getConscientiousness());
            double diffN = Math.abs(user.getNeuroticism() - clusterValues.getNeuroticism());
            double diffE = Math.abs(user.getExtraversion() - clusterValues.getExtraversion());
            double diffA = Math.abs(user.getAgreeableness() - clusterValues.getAgreeableness());
            double diffO = Math.abs(user.getOpenness() - clusterValues.getOpenness());
            double deviation = diffC + diffN + diffE + diffA + diffO;
            return String.format("%.02f", (deviation / 5) * 100);
        } else return "0";
    }

    public static String getDeviationNN(){
        User NN = getMostSimilarUser(user);
        if(NN == null)
            return null;
        double diffC = (user.getConscientiousness() - NN.getConscientiousness());
        double diffN = (user.getNeuroticism() - NN.getNeuroticism());
        double diffE = (user.getExtraversion() - NN.getExtraversion());
        double diffA = (user.getAgreeableness() - NN.getAgreeableness());
        double diffO = (user.getOpenness() - NN.getOpenness());
        double deviation = Math.sqrt(diffC*diffC + diffN*diffN + diffE*diffE + diffA*diffA + diffO*diffO);
        return String.format("%.02f",(deviation/5)*100);
    }

    public static List<User> getFriendshipRequests(){
        return API.getFriendRequests(user);
    }

    public static double[] getPersonalityValues(){
        return new double[]{user.getOpenness(),user.getAgreeableness(),user.getNeuroticism(),user.getExtraversion(),user.getConscientiousness(),user.getTimeSpent()};
    }

    public static List<User> getSimilarUsers(){
        return API.getSimilarUsers(user);
    }

    public static Song getMoreInformationSong(Song selectedSong){
        return API.getSong(selectedSong);
    }

    public static List<Song> getRecommendedSongs(){
        return API.getRecommendedSongs(user);
    }

    public static List<Song> getSongsByName(String name){
        return API.searchSongByName(name);
    }


    public static List<User> getUsersByUsername(String username){
        return API.searchUserByUsername(username);
    }

    public static void deleteSong(Song song){
        API.deleteSong(song);
    }

    public static void deleteUser(User user){
        API.deleteUser(user);
    }

    public static void quarantineUser(User user){
        API.quarantineUser(user);
    }

    public static void addNewSong(String name, String album, String artist, int year, String image, double danceability, double energy, double loudness, double speechiness, double acousticness, double instrumentalness, double liveness, double valence) {
        List<String> list = new ArrayList<String>();
        list.add(artist);
        API.createSong(new Song(name, album, list, year, image, danceability, energy, loudness, speechiness, acousticness, instrumentalness, liveness, valence));
    }

    public static List<User> getFriendships(){
        return API.getFriendships(user);
    }

    public static User getUserInfo(String id){
        return API.getUserInfo(id);
    }

    public static void loginUser(String loginEmail, String loginPassword) throws Exception {
        User userLogin = new User(loginEmail, loginPassword);
        user = API.loginUser(userLogin);
        System.out.println(user.getAdmin());
    }

    public static void addFriendRequest(String receiverId){
        API.addFriendRequest(user, receiverId);
    }

    public static void deleteFriendRequest(User receiver){
        API.updateFriendRequest(receiver, user, 0);
    }

    public static void acceptFriendRequest(User receiver){
        API.updateFriendRequest(user, receiver, 1);
    }

    public static List<User> getNearbyUsers(){
        return API.getNearbyUsers(user);
    }

    public static void checkForNewRecommended(){
        API.checkForUpdates(user);
    }
}
