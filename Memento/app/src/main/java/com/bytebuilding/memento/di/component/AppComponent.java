package com.bytebuilding.memento.di.component;

import com.bytebuilding.memento.di.module.ApplicationContextModule;
import com.bytebuilding.memento.di.module.MementoModelModule;
import com.bytebuilding.memento.di.module.RecyclerViewAdapterModule;
import com.bytebuilding.memento.di.module.SharedPreferencesModule;
import com.bytebuilding.memento.mvp.presenter.MementoListPresenter;
import com.bytebuilding.memento.ui.activity.IntroActivity;
import com.bytebuilding.memento.ui.activity.MainActivity;
import com.bytebuilding.memento.ui.fragment.content.MementoListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Turkin A. on 09.11.17.
 */

@Singleton
@Component(modules = {ApplicationContextModule.class,
        SharedPreferencesModule.class,
        MementoModelModule.class,
        RecyclerViewAdapterModule.class})
public interface AppComponent {

    //Activities
    void inject(MainActivity mainActivity);

    void inject(IntroActivity introActivity);

    //Fragments
    void inject(MementoListFragment mementoListFragment);

    //Presenters
    void inject(MementoListPresenter presenter);

}