package com.bytebuilding.memento.mvp.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bytebuilding.memento.mvp.model.Memento;

import java.util.List;

import io.reactivex.Single;

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
    List<Memento> getAllMementosList();

    @Query("SELECT * FROM memento")
    LiveData<List<Memento>> getAllMementosLiveData();

    @Query("SELECT * FROM memento WHERE id = :mementoId")
    Memento getMementoById(long mementoId);

    @Query("SELECT * FROM memento WHERE id = :mementoId")
    LiveData<Memento> getMementoLiveDataById(long mementoId);

    @Query("SELECT * FROM Memento LIMIT 1")
    Memento getFirstMemento();

    @Query("SELECT * FROM Memento LIMIT 1")
    LiveData<Memento> getFirstMementoLiveData();

    @Query("SELECT * FROM Memento LIMIT 1")
    Single<Memento> getFirstMementoSingle();

}
