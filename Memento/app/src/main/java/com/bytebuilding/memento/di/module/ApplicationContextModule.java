package com.bytebuilding.memento.di.module;

import android.app.Application;
import android.content.Context;

import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 09.11.17.
 */

@Module
public class ApplicationContextModule {

    Application mApplication;

    public ApplicationContextModule(Application appInstance) {
        this.mApplication = appInstance;
    }

    @Singleton
    @Provides
    @Named("ApplicationContextModule")
    Context provideContext() {
        return this.mApplication;
    }

}
