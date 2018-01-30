package com.bytebuilding.memento.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.media.MediaRecorder;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bytebuilding.memento.events.data.DataEvents;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.mvp.view.MainView;
import com.bytebuilding.memento.utils.MementoApplication;
import com.bytebuilding.memento.mvp.model.RecordingStates;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 11.11.2017.
 */

@InjectViewState
public class MainViewPresenter extends MvpPresenter<MainView> implements LifecycleObserver {

    public static final String TAG = "MainViewPresenter";

    @Inject
    MementoModel mModel;

    @Inject
    MediaRecorder mAudioRecorder;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private RecordingStates mRecordingStates;

    public MainViewPresenter() {
        MementoApplication.getAppComponent().inject(this);
        catcherEvents();
        mRecordingStates = RecordingStates.DEFAULT_STATE;
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
                                switch (mRecordingStates) {
                                    case DEFAULT_STATE:
                                        getViewState().startRecording();
                                        mRecordingStates = RecordingStates.START_RECORDING;
                                        break;

                                    case START_RECORDING:
                                        getViewState().pauseRecording();
                                        mRecordingStates = RecordingStates.PAUSE_RECORDING;
                                        break;

                                    case PAUSE_RECORDING:
                                        getViewState().startRecording();
                                        mRecordingStates = RecordingStates.START_RECORDING;
                                        break;
                                }
                            } else if (o instanceof UiEvents.StopRecordingFabEvent) {
                                getViewState().stopRecording();
                                mRecordingStates = RecordingStates.DEFAULT_STATE;
                            }
                        })
        );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void freeMemory() {
        mDisposable.clear();
    }
}