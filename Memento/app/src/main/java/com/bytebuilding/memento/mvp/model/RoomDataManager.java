package com.bytebuilding.memento.mvp.model;

import android.arch.persistence.room.RoomDatabase;

import com.bytebuilding.memento.mvp.model.database.AppMementoDatabase;

import javax.inject.Inject;

/**
 * Created by Turkin A. on 26.01.2018.
 */

public class RoomDataManager extends DataManager {

    public static final String TAG = "RoomDataManager";

    @Inject
    AppMementoDatabase mDatabase;

    @Override
    public void saveMemento(Memento memento) {
        mDatabase.mementoDao().insert(memento);
    }

    public void saveMementoTwo() {

    }
}
