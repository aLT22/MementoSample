package com.bytebuilding.memento.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bytebuilding.memento.mvp.view.MainView;
import com.bytebuilding.memento.utils.AppUtilities;

/**
 * Created by Turkin A. on 11.11.2017.
 */

@InjectViewState
public class MainViewPresenter extends MvpPresenter<MainView> {

    public MainViewPresenter() {
    }

    public void setFragment() {
        if (AppUtilities.isFolderEmpty()) {
            getViewState().showEmptyScreen();
        } else {
            getViewState().showMementosScreen();
        }
    }

}
