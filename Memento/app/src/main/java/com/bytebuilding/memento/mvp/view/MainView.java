package com.bytebuilding.memento.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Turkin A. on 11.11.2017.
 */

public interface MainView extends MvpView {

    void showMementosScreen();

    void showEmptyScreen();

    void startRecording();

    void pauseRecording();

    void stopRecording();

}
