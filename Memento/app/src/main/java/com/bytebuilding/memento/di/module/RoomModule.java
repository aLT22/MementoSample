package com.bytebuilding.memento.di.module;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bytebuilding.memento.mvp.model.database.AppMementoDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 29.01.2018.
 */

@Module(includes = {ApplicationContextModule.class})
public class RoomModule {

    @Provides
    @Singleton
    @Named("RoomModule")
    AppMementoDatabase provideMementoDatabase(@Named("ApplicationContextModule") Context context) {
        return Room.databaseBuilder(context, AppMementoDatabase.class, "memento_database").build();
    }

}
