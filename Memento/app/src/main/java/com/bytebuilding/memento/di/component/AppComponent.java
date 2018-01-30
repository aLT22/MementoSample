package com.bytebuilding.memento.di.component;

import com.bytebuilding.memento.di.module.ApplicationContextModule;
import com.bytebuilding.memento.di.module.MediaRecorderModule;
import com.bytebuilding.memento.di.module.MementoModelModule;
import com.bytebuilding.memento.di.module.RecyclerViewAdapterModule;
import com.bytebuilding.memento.di.module.RoomDatabaseModule;
import com.bytebuilding.memento.di.module.SharedPreferencesModule;
import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.mvp.presenter.MainViewPresenter;
import com.bytebuilding.memento.mvp.presenter.MementoListPresenter;
import com.bytebuilding.memento.ui.activity.MainActivity;
import com.bytebuilding.memento.ui.activity.OnboardingActivity;
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
        RecyclerViewAdapterModule.class,
        RoomDatabaseModule.class,
        MediaRecorderModule.class})
public interface AppComponent {

    //Activities
    void inject(MainActivity mainActivity);

    void inject(OnboardingActivity onboardingActivity);

    //Fragments
    void inject(MementoListFragment mementoListFragment);

    //Presenters
    void inject(MementoListPresenter presenter);

    void inject(MainViewPresenter presenter);

    //Model
    void inject(MementoModel model);

}
