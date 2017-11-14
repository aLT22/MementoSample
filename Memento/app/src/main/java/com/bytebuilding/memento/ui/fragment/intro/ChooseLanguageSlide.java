package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.utils.MementoApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseLanguageSlide extends Fragment {

    @BindView(R.id.ib_rus)
    ImageButton mRusLang;

    @BindView(R.id.ib_uk)
    ImageButton mUkLang;

    private Unbinder mUnbinder = null;

    public static ChooseLanguageSlide newInstance() {
        ChooseLanguageSlide fragment = new ChooseLanguageSlide();

        return fragment;
    }

    public static ChooseLanguageSlide newInstance(Bundle bundle) {
        ChooseLanguageSlide fragment = new ChooseLanguageSlide();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_language_slide, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    @OnClick(R.id.ib_rus)
    public void onRusButtonClick() {
        MementoApplication
                .bus()
                .send(new UiEvents.ChooseRussianLanguageEvent());
    }

    @OnClick(R.id.ib_uk)
    public void onUkButtonClick() {
        MementoApplication
                .bus()
                .send(new UiEvents.ChooseEnglishLanguageEvent());
    }
}