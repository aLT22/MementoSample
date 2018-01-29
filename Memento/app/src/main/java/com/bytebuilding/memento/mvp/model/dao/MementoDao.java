package com.bytebuilding.memento.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bytebuilding.memento.mvp.model.Memento;

import java.util.List;

/**
 * Created by Turkin A. on 29.01.2018.
 */

@Dao
public interface MementoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Memento memento);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Memento... mementos);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Memento> mementos);

    @Update
    void update(Memento memento);

    @Update
    void update(Memento... mementos);

    @Update
    void update(List<Memento> mementos);

    @Delete
    void delete(Memento memento);

    @Delete
    void delete(Memento... mementos);

    @Delete
    void delete(List<Memento> mementos);

    //Queries

    @Query("SELECT * FROM memento")
    List<Memento> getAllMementos();

}
