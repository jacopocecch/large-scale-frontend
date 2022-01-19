package com.unipi.datamining.beans;

import com.unipi.datamining.entities.Song;
import com.unipi.datamining.entities.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.Objects;

public class SongBean {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty album;
    private SimpleStringProperty artists;
    private SimpleStringProperty releaseDate;
    private ImageView image;


    public SongBean(Song song){
        id = new SimpleStringProperty(song.getId());
        name = new SimpleStringProperty(song.getName());
        album = new SimpleStringProperty(song.getAlbum());
        artists = new SimpleStringProperty(String.join(",", song.getArtists()));
        image = new ImageView(song.getImage());
        releaseDate = new SimpleStringProperty(song.getReleaseDate());
        image.setFitHeight(30);
        image.setFitWidth(30);
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public String getReleaseDate() {
        return releaseDate.get();
    }

    public void setArtists(String artists) {
        this.artists.set(artists);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }

    public String getAlbum() {
        return album.get();
    }

    public String getArtists() {
        return artists.get();
    }

    public String getName() {
        return name.get();
    }

    public String getId() {
        return id.get();
    }
}


