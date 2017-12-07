package com.bytebuilding.memento.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Turkin A. on 30.11.2017.
 */

public interface OnboardingView extends MvpView {

    void showMainView();

    void showNextSlide(int pageNumber);

    void changeButtonStatus();

}
