package com.bytebuilding.memento.utils;

import android.app.Application;
import android.util.Log;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.di.component.AppComponent;
import com.bytebuilding.memento.di.component.DaggerAppComponent;
import com.bytebuilding.memento.di.module.ApplicationContextModule;
import com.bytebuilding.memento.di.module.MediaRecorderModule;
import com.bytebuilding.memento.di.module.MementoModelModule;
import com.bytebuilding.memento.di.module.RecyclerViewAdapterModule;
import com.bytebuilding.memento.di.module.RoomDatabaseModule;
import com.bytebuilding.memento.di.module.SharedPreferencesModule;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.rx.MementoRxBus;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 19.10.17.
 */

public class MementoApplication extends Application {

    public static final String TAG = "MementoApplication";

    private static MementoRxBus mBus;

    private static AppComponent sDaggerComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = new MementoRxBus();

        buildDagger();

        Observable
                .interval(100, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (UiEvents.ChangeArrowColorEvent.sArrowIndex == 6) {
                        UiEvents.ChangeArrowColorEvent.sArrowIndex = 0;
                    } else {
                        UiEvents.ChangeArrowColorEvent.sArrowIndex++;
                    }
                    MementoApplication.bus().send(new UiEvents.ChangeArrowColorEvent());
                });
    }

    public static MementoRxBus bus() {
        return mBus;
    }

    public static synchronized AppComponent getAppComponent() {
        return sDaggerComponent;
    }

    private synchronized void buildDagger() {
        sDaggerComponent = DaggerAppComponent
                .builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .mementoModelModule(new MementoModelModule())
                .recyclerViewAdapterModule(new RecyclerViewAdapterModule())
                .sharedPreferencesModule(new SharedPreferencesModule())
                .roomDatabaseModule(new RoomDatabaseModule())
                .mediaRecorderModule(new MediaRecorderModule())
                .roomDatabaseModule(new RoomDatabaseModule())
                .build();
    }
}
