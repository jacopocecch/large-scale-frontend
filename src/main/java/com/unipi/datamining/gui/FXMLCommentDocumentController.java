package com.unipi.datamining.gui;

import com.unipi.datamining.PersonalityClustering;
import com.unipi.datamining.beans.CommentBean;
import com.unipi.datamining.beans.SongBean;
import com.unipi.datamining.entities.Comment;
import com.unipi.datamining.entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.unipi.datamining.PersonalityClustering.*;


public class FXMLCommentDocumentController implements Initializable{
    @FXML
    private TableColumn<CommentBean, String> nameColumn;
    @FXML
    private TableColumn<CommentBean, String> surnameColumn;
    @FXML
    private TableColumn<CommentBean, String> textColumn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextArea newComment;
    @FXML
    private TableView<CommentBean> tableView;

    @FXML
    private void showSongInfo(ActionEvent event){
        System.out.println("Loading the song information page");
        LoaderFXML object = new LoaderFXML();
        Pane songInfoPane = object.getPage("songInfo");
        try {
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(songInfoPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void commentSong(ActionEvent event){
        System.out.println("Commenting the song");
        String comment = newComment.getText();
        PersonalityClustering.commentSong(comment, new Song(FXMLSongInfoDocumentController.selectedSong));
        // fai e ricarica pagina
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        List<Comment> songComments = getComments(new Song(FXMLSongInfoDocumentController.selectedSong));
        ObservableList<CommentBean> commentBeans = FXCollections.observableArrayList();
        if(songComments != null) {
            for (Comment comment : songComments) {
                commentBeans.add(new CommentBean(comment));
            }
        }
        tableView.setItems(commentBeans);
    }
}
