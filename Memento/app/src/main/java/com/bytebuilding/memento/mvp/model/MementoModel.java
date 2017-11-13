package com.bytebuilding.memento.mvp.model;

import android.os.Environment;

import com.bytebuilding.memento.events.ui.DataEvents;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class MementoModel {

    public static final String TAG = MementoModel.class.getSimpleName();

    private List<Memento> mMementos = new LinkedList<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public MementoModel() {
    }

    public void setFakeMementos() {
        Observable.fromCallable(new Callable<List<Memento>>() {
            @Override
            public List<Memento> call() throws Exception {
                return getFakeData();
            }
        })
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<Memento>, ObservableSource<Memento>>() {
                    @Override
                    public ObservableSource<Memento> apply(List<Memento> mementos) throws Exception {
                        return Observable.fromIterable(mementos);
                    }
                })
                .subscribe(new Observer<Memento>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Memento memento) {
                        mMementos.add(memento);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        MementoApplication
                                .bus()
                                .send(new DataEvents.DataWasFetched());
                    }
                });
    }

    private List<Memento> getFakeData() {
        List<Memento> mementos = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            mementos.add(new Memento("Title " + String.valueOf(i),
                    "Description " + String.valueOf(i),
                    "Date " + String.valueOf(i),
                    Environment.getExternalStorageDirectory() + File.pathSeparator + AppUtilities.Constants.APP_NAME));
        }

        return mementos;
    }

    public List<Memento> getmMementos() {
        return this.mMementos;
    }

    public void freeMemory() {
        mDisposable.dispose();
    }

}
