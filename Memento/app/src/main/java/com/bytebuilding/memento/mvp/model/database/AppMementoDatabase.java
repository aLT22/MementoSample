package com.bytebuilding.memento.mvp.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bytebuilding.memento.mvp.model.Memento;
import com.bytebuilding.memento.mvp.model.dao.MementoDao;

/**
 * Created by Turkin A. on 29.01.2018.
 */

@Database(entities = {Memento.class}, version = 1)
public abstract class AppMementoDatabase extends RoomDatabase {
    public abstract MementoDao mementoDao();
}
