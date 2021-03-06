package com.bytebuilding.memento.mvp.model;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class MementoDemo {

    String title;

    String description;

    String date;

    String path;

    public MementoDemo(String title, String description, String date, String path) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MementoDemo mementoDemo = (MementoDemo) o;

        if (title != null ? !title.equals(mementoDemo.title) : mementoDemo.title != null) return false;
        if (description != null ? !description.equals(mementoDemo.description) : mementoDemo.description != null) return false;
        return date != null ? date.equals(mementoDemo.date) : mementoDemo.date == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
