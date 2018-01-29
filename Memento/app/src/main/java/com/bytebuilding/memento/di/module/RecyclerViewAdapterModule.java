package com.bytebuilding.memento.di.module;

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

@Module(includes = {ApplicationContextModule.class, MementoModelModule.class})
public class RecyclerViewAdapterModule {

    @Provides
    @Singleton
    MementoRecyclerAdapter provideRecyclerAdapter(@Named("ApplicationContextModule") Context context,
                                                  @Named("MementoModelModule") MementoModel model) {
        return new MementoRecyclerAdapter(context, model.getmMementoDemos());
    }

}
