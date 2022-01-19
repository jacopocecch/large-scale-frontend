package com.unipi.datamining.gui;

import com.unipi.datamining.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.unipi.datamining.PersonalityClustering.*;
import static com.unipi.datamining.gui.ValidationForm.validDate;
import static com.unipi.datamining.gui.ValidationForm.validEmailAddress;
import static com.unipi.datamining.util.UtilGUI.readListOfCountries;


public class FXMLUserInfoDocumentController implements Initializable{
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField dateOfBirthInput;
    @FXML
    private TextField genderInput;
    @FXML
    private TextField countryInput;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = getUserInfo(FXMLFriendshipsDocumentController.userInfoID);
        FXMLFriendshipsDocumentController.userInfoID = null;
        image.setImage(new Image(user.getImage()));
        name.setText(user.getFirstName());
        name.setEditable(false);
        surname.setText(user.getLastName());
        surname.setEditable(false);
        username.setText(user.getUsername());
        username.setEditable(false);
        email.setText(user.getEmail());
        email.setEditable(false);
        phoneNumber.setText(user.getPhoneNumber());
        phoneNumber.setEditable(false);
        genderInput.setText(user.getGender());
        genderInput.setEditable(false);
        dateOfBirthInput.setText(user.getDateOfBirth().toString());
        dateOfBirthInput.setEditable(false);
        countryInput.setText(user.getCountry());
        countryInput.setEditable(false);
    }
}
