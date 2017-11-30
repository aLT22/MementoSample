package com.bytebuilding.memento.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bytebuilding.memento.mvp.view.OnboardingView;
import com.bytebuilding.memento.utils.MementoApplication;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 30.11.2017.
 */

@InjectViewState
public class OnboardingViewPresenter extends MvpPresenter<OnboardingView> {

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public OnboardingViewPresenter() {
        catchEvents();
    }

    private void catchEvents() {
        mDisposable.add(
                MementoApplication
                        .bus()
                        .observable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o -> {
                        })
        );
    }

}
