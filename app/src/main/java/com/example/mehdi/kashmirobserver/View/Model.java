package com.example.mehdi.kashmirobserver.View;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Model {
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public Model() {
    }

    public Model(String name,  int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }
    public Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
