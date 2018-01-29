package com.bytebuilding.memento.ui.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bytebuilding.memento.ui.fragment.intro.IntroductionSlide;
import com.bytebuilding.memento.ui.fragment.intro.JustPressButtonSlide;
import com.bytebuilding.memento.ui.fragment.intro.PressOnceMoreSlide;
import com.bytebuilding.memento.ui.fragment.intro.StopRecordingSlide;
import com.bytebuilding.memento.utils.AppUtilities;

/**
 * Created by Turkin A. on 30.11.2017.
 */

public class OnboardingViewPagerAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {

    public static final String TAG = "OnboardingViewPagerAdapter";

    public OnboardingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case AppUtilities.Constants.INTRODUCTION_SLIDE_PAGE:
                return IntroductionSlide.newInstance();

            case AppUtilities.Constants.JUST_PRESS_BUTTON_SLIDE_PAGE:
                return JustPressButtonSlide.newInstance();

            case AppUtilities.Constants.PRESS_ONCE_MORE_SLIDE_PAGE:
                return PressOnceMoreSlide.newInstance();

            case AppUtilities.Constants.STOP_RECORDING_SLIDE_PAGE:
                return StopRecordingSlide.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return AppUtilities.Constants.PAGES_COUNT;
    }

    @Override
    public void transformPage(View page, float position) {

    }
}
