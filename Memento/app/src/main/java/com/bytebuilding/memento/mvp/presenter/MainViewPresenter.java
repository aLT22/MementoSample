package com.bytebuilding.memento.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bytebuilding.memento.events.data.DataEvents;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.mvp.view.MainView;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 11.11.2017.
 */

@InjectViewState
public class MainViewPresenter extends MvpPresenter<MainView> implements LifecycleObserver {

    public static final String TAG = "MainViewPresenter";

    @Inject
    MementoModel mModel;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public MainViewPresenter() {
        catcherEvents();
        MementoApplication.getAppComponent().inject(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void setFragment() {
        mModel.isDatabaseEmpty();
    }

    private void catcherEvents() {
        catchUiEvents();
        catchDataEvents();
    }

    private void catchDataEvents() {
        mDisposable.add(
                MementoApplication
                        .bus()
                        .observable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o -> {
                            if (o instanceof DataEvents.DatabaseIsEmptyEvent) {
                                getViewState().showEmptyScreen();
                            } else if (o instanceof DataEvents.DatabaseIsNotEmptyEvent) {
                                getViewState().showMementosScreen();
                            }
                        })
        );
    }

    private void catchUiEvents() {
        mDisposable.add(
                MementoApplication
                        .bus()
                        .observable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o -> {
                            if (o instanceof UiEvents.ShowSettingsEvent) {
                                getViewState().showSettings();
                            } else if (o instanceof UiEvents.AnimateFabEvent) {
                                getViewState().startRecording();
                            }
                        })
        );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void freeMemory() {
        mDisposable.clear();
    }
}