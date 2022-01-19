package com.unipi.datamining.entities;

import com.unipi.datamining.beans.SongBean;
import com.unipi.datamining.beans.UserBean;
import com.unipi.datamining.dtos.Neo4jUserDto;
import com.unipi.datamining.dtos.UserDto;

import java.time.LocalDate;

public class Song {
    private String id;
    private String image;
    private String name;
    private String album;
    private String[] artists;
    private String releaseDate;
    private int trackNumber;
    private int discNumber;
    private boolean explicit;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private int mode;
    private double speechness;

    public Song(String id, String name, String album, String[] artists, String image, String releaseDate){
        this.id = id;
        this.name = name;
        this.album = album;
        this.artists = artists;
        if(image == null)
            this.image = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Circle-icons-music.svg/1024px-Circle-icons-music.svg.png";
        else
            this.image = image;
    }

    public Song(SongBean songBean){
        this.id = songBean.getId();
        this.name = songBean.getName();
        // to do
    }

    public String getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAlbum() {
        return album;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String[] getArtists() {
        return artists;
    }

    public void setArtists(String[] artists) {
        this.artists = artists;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}


