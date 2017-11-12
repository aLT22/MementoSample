package com.bytebuilding.memento.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.mvp.view.MementoListView;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Inject;

/**
 * Created by Turkin A. on 12.11.2017.
 */

@InjectViewState
public class MementoListPresenter extends MvpPresenter<MementoListView> {

    MementoModel mModel;

    public MementoListPresenter(MementoModel model) {
        this.mModel = model;
    }

}
