package com.bytebuilding.memento.mvp.model;

import android.os.Environment;

import com.bytebuilding.memento.events.data.DataEvents;
import com.bytebuilding.memento.mvp.model.database.AppMementoDatabase;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class MementoModel {

    public static final String TAG = "MementoModel";

    private List<MementoDemo> mMementoDemos = new LinkedList<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    AppMementoDatabase mDatabase;

    public MementoModel() {
        MementoApplication.getAppComponent().inject(this);
    }

    public void setFakeMementos() {
        Observable
                .fromCallable(this::getFakeData)
                .subscribeOn(Schedulers.io())
                .flatMap(Observable::fromIterable)
                .subscribe(new Observer<MementoDemo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MementoDemo mementoDemo) {
                        mMementoDemos.add(mementoDemo);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        MementoApplication
                                .bus()
                                .send(new DataEvents.DataWasFetchedEvent());
                    }
                });
    }

    private List<MementoDemo> getFakeData() {
        List<MementoDemo> mementoDemos = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            mementoDemos.add(new MementoDemo("Title " + String.valueOf(i),
                    "Description " + String.valueOf(i),
                    "Date " + String.valueOf(i),
                    Environment.getExternalStorageDirectory() + File.pathSeparator + AppUtilities.Constants.APP_NAME));
        }

        return mementoDemos;
    }

    public List<MementoDemo> getmMementoDemos() {
        return this.mMementoDemos;
    }

    public void isDatabaseEmpty() {
        mDisposable
                .add(
                        Observable
                                .fromCallable(() -> mDatabase.mementoDao().getFirstMemento())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(memento -> {
                                    if (memento == null) {
                                        MementoApplication
                                                .bus()
                                                .send(new DataEvents.DatabaseIsEmptyEvent());
                                    } else {
                                        MementoApplication
                                                .bus()
                                                .send(new DataEvents.DatabaseIsNotEmptyEvent());
                                    }
                                }, (throwable -> {
                                    MementoApplication
                                            .bus()
                                            .send(new DataEvents.DatabaseIsEmptyEvent());
                                }))
                );
    }

    public void freeMemory() {
        mDisposable.dispose();
    }

}
