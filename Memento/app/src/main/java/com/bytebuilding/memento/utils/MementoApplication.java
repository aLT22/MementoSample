package com.bytebuilding.memento.utils;

import android.app.Application;

import com.bytebuilding.memento.rx.MementoRxBus;

/**
 * Created by Turkin A. on 19.10.17.
 */

public class MementoApplication extends Application {

    private static MementoRxBus mBus;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = new MementoRxBus();
    }

    public static MementoRxBus bus() {
        return mBus;
    }
}
