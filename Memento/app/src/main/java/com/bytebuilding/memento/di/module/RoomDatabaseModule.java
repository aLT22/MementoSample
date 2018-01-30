package com.bytebuilding.memento.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.bytebuilding.memento.mvp.model.database.AppMementoDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 30.01.2018.
 */

@Module
public class RoomDatabaseModule {

    @Provides
    @Singleton
    AppMementoDatabase provideRoomDatabase(Application application) {
        return Room.databaseBuilder(application, AppMementoDatabase.class, "memento_database").build();
    }

}
