package com.bytebuilding.memento.mvp.model;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class Memento {

    String title;

    String description;

    String date;

    String path;

    public Memento(String title, String description, String date, String path) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
