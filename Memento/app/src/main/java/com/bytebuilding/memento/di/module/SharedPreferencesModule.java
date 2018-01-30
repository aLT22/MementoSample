package com.bytebuilding.memento.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.bytebuilding.memento.utils.AppUtilities;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 09.11.17.
 */

@Module
public class SharedPreferencesModule {

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(AppUtilities.Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    SharedPreferences.Editor provideSharedPreferencesEditor(Application application) {
        return application.getSharedPreferences(AppUtilities.Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
    }

}
