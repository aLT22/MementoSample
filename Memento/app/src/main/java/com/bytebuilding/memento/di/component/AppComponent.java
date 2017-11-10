package com.bytebuilding.memento.di.component;

import com.bytebuilding.memento.di.module.ApplicationContextModule;
import com.bytebuilding.memento.di.module.SharedPreferencesModule;
import com.bytebuilding.memento.ui.activity.IntroActivity;
import com.bytebuilding.memento.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Turkin A. on 09.11.17.
 */

@Singleton
@Component(modules = {ApplicationContextModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    //Activities
    void inject(MainActivity mainActivity);
    void inject(IntroActivity introActivity);

}
