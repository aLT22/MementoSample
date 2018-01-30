package com.bytebuilding.memento.di.module;

import com.bytebuilding.memento.mvp.model.DataManager;
import com.bytebuilding.memento.mvp.model.RoomDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 30.01.2018.
 */

@Module
public class DataManagerModule {

    @Singleton
    @Provides
    RoomDataManager provideDataManager() {
        return new RoomDataManager();
    }

}
