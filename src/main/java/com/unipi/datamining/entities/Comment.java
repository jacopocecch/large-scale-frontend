package com.unipi.datamining.entities;

import javafx.beans.property.SimpleStringProperty;

public class Comment {
    private String id;
    private String name;
    private String surname;
    private String text;


    public Comment(String id, String name, String surname, String text){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getText() {
        return text;
    }
}



