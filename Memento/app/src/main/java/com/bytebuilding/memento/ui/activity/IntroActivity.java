package com.bytebuilding.memento.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.ui.fragment.intro.ChooseLanguageFragment;
import com.bytebuilding.memento.ui.fragment.intro.FirstSlide;
import com.bytebuilding.memento.ui.fragment.intro.SecondSlide;
import com.bytebuilding.memento.utils.MementoApplication;
import com.github.paolorotolo.appintro.AppIntro;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 20.10.2017.
 */

public class IntroActivity extends AppIntro {

    public static final String TAG = IntroActivity.class.getSimpleName();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(ChooseLanguageFragment.newInstance());

        addSlide(FirstSlide.newInstance());

        addSlide(SecondSlide.newInstance());

        showSkipButton(false);
        setProgressButtonEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDisposable.dispose();
    }
}
