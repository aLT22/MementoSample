package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.ui.animation.intro.IntroAnimator;
import com.bytebuilding.memento.ui.animation.intro.IntroductionSlideAnimator;
import com.bytebuilding.memento.utils.AppUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IntroductionSlide extends Fragment {

    public static final String TAG = "IntroductionSlide";

    @BindView(R.id.iv_speaking)
    ImageView mSpeakingHead;

    @BindView(R.id.iv_smartphone)
    ImageView mSmartphone;

    @BindView(R.id.tv_description_introduction)
    TextView mDescription;

    Unbinder mUnbinder = null;

    public static IntroductionSlide newInstance() {
        IntroductionSlide fragment = new IntroductionSlide();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_introduction_slide, container, false);

        rootView.setTag(AppUtilities.Constants.INTRODUCTION_VALUE);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public class IntroductionSlidesPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            IntroAnimator pagerAnimator = new IntroductionSlideAnimator();
            pagerAnimator.animateSlide(page, position, R.id.tv_description_introduction, AppUtilities.Constants.INTRODUCTION_VALUE);
        }
    }
}
