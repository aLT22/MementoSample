package com.bytebuilding.memento.di.module;

import android.app.Application;
import android.content.Context;

import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.ui.adapter.MementoRecyclerAdapter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 12.11.2017.
 */

@Module
public class RecyclerViewAdapterModule {

    @Provides
    @Singleton
    MementoRecyclerAdapter provideRecyclerAdapter(Application application,
                                                  MementoModel model) {
        return new MementoRecyclerAdapter(application, model.getmMementoDemos());
    }

}
