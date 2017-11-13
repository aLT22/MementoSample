package com.bytebuilding.memento.utils;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.bytebuilding.memento.di.component.AppComponent;
import com.bytebuilding.memento.di.component.DaggerAppComponent;
import com.bytebuilding.memento.di.module.ApplicationContextModule;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.rx.MementoRxBus;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 19.10.17.
 */

public class MementoApplication extends Application {

    public static final String TAG = MementoApplication.class.getSimpleName();

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
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (UiEvents.ChangeArrowColorEvent.sArrowIndex == 6) {
                            UiEvents.ChangeArrowColorEvent.sArrowIndex = 0;
                        } else {
                            UiEvents.ChangeArrowColorEvent.sArrowIndex++;
                        }
                        MementoApplication.bus().send(new UiEvents.ChangeArrowColorEvent());
                    }
                });

        appFolderCreation();
    }

    private void appFolderCreation() {
        Log.e(TAG, "appFolderCreation: " + Environment.getExternalStorageDirectory());
        File folder = new File(Environment.getExternalStorageDirectory() + "/yourDirectoryName");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
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
                .build();
    }
}
