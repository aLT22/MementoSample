package com.bytebuilding.memento.mvp.model;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Turkin A. on 26.01.2018.
 */

public class Memento {

    private long id;

    byte[] content;

    String title;

    String description;

    Date date;

    public Memento() {
        this.content = new byte[0];
        this.title = "Default title";
        this.description = "Default description";
        this.date = new Date(System.currentTimeMillis());
    }

    public Memento(long id, byte[] content, String title, String description, Date date) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Memento buildId(long id) {
        this.setId(id);
        return this;
    }

    public Memento buildContent(byte[] content) {
        this.setContent(content);
        return this;
    }

    public Memento buildTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public Memento buildDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public Memento buildDate(Date date) {
        this.setDate(date);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memento memento = (Memento) o;

        if (id != memento.id) return false;
        if (!Arrays.equals(content, memento.content)) return false;
        if (title != null ? !title.equals(memento.title) : memento.title != null) return false;
        if (description != null ? !description.equals(memento.description) : memento.description != null) return false;
        return date != null ? date.equals(memento.date) : memento.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
