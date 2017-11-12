package com.bytebuilding.memento.mvp.model;

import android.os.Environment;

import com.bytebuilding.memento.utils.AppUtilities;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class MementoModel {

    private List<Memento> mementos;

    public MementoModel() {
        mementos = new LinkedList<>();
    }

    public void setFakeMementos() {
        for (int i = 0; i < 25; i++) {
            mementos.add(new Memento("Title " + String.valueOf(i),
                    "Description " + String.valueOf(i),
                    "Date " + String.valueOf(i),
                    Environment.getExternalStorageDirectory() + File.pathSeparator + AppUtilities.Constants.APP_NAME));
        }
    }

    public List<Memento> getMementos() {
        return this.mementos;
    }

}
