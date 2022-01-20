package com.unipi.datamining.gui;

import com.unipi.datamining.beans.SongBean;
import com.unipi.datamining.beans.UserBean;
import com.unipi.datamining.entities.Song;
import com.unipi.datamining.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.unipi.datamining.PersonalityClustering.*;


public class FXMLUsersAdminDocumentController implements Initializable{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField username;
    @FXML
    private TableColumn<UserBean, String> nameColumn;
    @FXML
    private TableColumn<UserBean, String> surnameColumn;
    @FXML
    private TableColumn<UserBean, String> deleteColumn;
    @FXML
    private TableColumn<UserBean, String> quarantineColumn;
    @FXML
    private TableColumn<UserBean, ImageView> imageColumn;
    @FXML
    private TableView<UserBean> tableView;

    @FXML
    private void searchUser(ActionEvent event){
        String name = username.getText();
        List<User> users = getUsersByUsername(name);
        updateTable(users);
    }

    private void updateTable(List<User> users) {
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        Callback<TableColumn<UserBean, String>, TableCell<UserBean, String>> cellFactory
                = //
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<UserBean, String> param) {
                        return new TableCell<UserBean, String>() {
                            final Button btn = new Button("Delete");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    btn.setStyle("-fx-background-color: #CCE5FF");
                                    btn.setOnAction(event -> {
                                        UserBean user = getTableView().getItems().get(getIndex());
                                        deleteUser(new User(user));
                                        System.out.println("The admin has deleted a user");
                                        searchUser(event);
                                    });
                                    setGraphic(btn);
                                }
                                setText(null);
                            }
                        };
                    }
                };
        deleteColumn.setCellFactory(cellFactory);
        cellFactory = //
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<UserBean, String> param) {
                        return new TableCell<UserBean, String>() {
                            final Button btn = new Button("Quarantine");
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    btn.setStyle("-fx-background-color: #CCE5FF");
                                    btn.setOnAction(event -> {
                                        UserBean user = getTableView().getItems().get(getIndex());
                                        quarantineUser(new User(user));
                                        System.out.println("The admin has quarantined a user");
                                        searchUser(event);
                                    });
                                    setGraphic(btn);
                                }
                                setText(null);
                            }
                        };
                    }
                };
        quarantineColumn.setCellFactory(cellFactory);
        ObservableList<UserBean> userBeans = FXCollections.observableArrayList();
        if (users != null) {
            for (User user : users) {
                userBeans.add(new UserBean(user));
            }
        }
        tableView.setItems(userBeans);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
