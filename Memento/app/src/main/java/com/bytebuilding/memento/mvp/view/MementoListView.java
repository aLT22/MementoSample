package com.bytebuilding.memento.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public interface MementoListView extends MvpView {

    void showMementosList();

    void showProgressBar();

    void hideProgressBar();

}
