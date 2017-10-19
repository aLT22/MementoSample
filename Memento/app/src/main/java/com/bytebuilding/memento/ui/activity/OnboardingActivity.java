package com.bytebuilding.memento.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bytebuilding.memento.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

/**
 * Created by Turkin A. on 19.10.17.
 */

public class OnboardingActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableLastSlideAlphaExitTransition(true);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimaryLight)
                .image(R.drawable.ic_speaking)
                .title("Memento")
                .description("Welcome to Memento! All you want to keep in mind here!")
                .build());
    }
}
