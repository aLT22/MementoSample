package com.bytebuilding.memento.ui.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.ui.fragment.intro.ChooseLanguageFragment;
import com.bytebuilding.memento.ui.fragment.intro.IntroductionSlide;
import com.bytebuilding.memento.ui.fragment.intro.JustPressButtonSlide;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;
import com.github.paolorotolo.appintro.AppIntro;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 20.10.2017.
 */

public class IntroActivity extends AppIntro {

    public static final String TAG = IntroActivity.class.getSimpleName();

    @Inject
    SharedPreferences.Editor mPreferencesEditor;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        catchEvents();

        addSlide(ChooseLanguageFragment.newInstance());

        addSlide(IntroductionSlide.newInstance());

        addSlide(JustPressButtonSlide.newInstance());

        showSkipButton(false);
        setProgressButtonEnabled(true);
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
}
