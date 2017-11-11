package com.bytebuilding.memento.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.view.View;

import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.ui.fragment.intro.ChooseLanguageSlide;
import com.bytebuilding.memento.ui.fragment.intro.IntroductionSlide;
import com.bytebuilding.memento.ui.fragment.intro.JustPressButtonSlide;
import com.bytebuilding.memento.ui.fragment.intro.PressOnceMoreSlide;
import com.bytebuilding.memento.ui.fragment.intro.StopRecordingSlide;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;
import com.github.paolorotolo.appintro.AppIntro;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 20.10.2017.
 */

// TODO: 09.11.17 In the future the screen should be changed to MVP concept like other screens
public class IntroActivity extends AppIntro {

    public static final String TAG = IntroActivity.class.getSimpleName();

    @Inject
    SharedPreferences.Editor mPreferencesEditor;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MementoApplication.getAppComponent().inject(this);

        catchEvents();

        setTutorialSlides();
        showSkipButton(false);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        startMainActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDisposable.dispose();
    }

    private void catchEvents() {
        catchUiEvents();
    }

    private void catchUiEvents() {
        mDisposable.add(
                MementoApplication
                        .bus()
                        .observable()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                if (o instanceof UiEvents.ChooseRussianLanguageEvent) {
                                    changeLanguage(AppUtilities.Constants.RU);
                                    mPreferencesEditor.putString(AppUtilities.Constants.KEY_APP_LANGUAGE,
                                            AppUtilities.Constants.RU);
                                    mPreferencesEditor.apply();
                                } else if (o instanceof UiEvents.ChooseEnglishLanguageEvent) {
                                    changeLanguage(AppUtilities.Constants.EN);
                                    mPreferencesEditor.putString(AppUtilities.Constants.KEY_APP_LANGUAGE,
                                            AppUtilities.Constants.EN);
                                    mPreferencesEditor.apply();
                                }
                            }
                        }));
    }

    private void changeLanguage(String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    private void setTutorialSlides() {
        addSlide(ChooseLanguageSlide.newInstance());

        addSlide(IntroductionSlide.newInstance());

        addSlide(JustPressButtonSlide.newInstance());

        addSlide(PressOnceMoreSlide.newInstance());

        addSlide(StopRecordingSlide.newInstance());
    }

    private void startMainActivity() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
