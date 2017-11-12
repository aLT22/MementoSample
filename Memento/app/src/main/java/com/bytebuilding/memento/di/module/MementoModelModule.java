package com.bytebuilding.memento.di.module;

import com.bytebuilding.memento.mvp.model.MementoModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 12.11.2017.
 */

@Module
public class MementoModelModule {

    @Singleton
    @Provides
    @Named("MementoModelModule")
    MementoModel provideMementoModel() {
        return new MementoModel();
    }

}
