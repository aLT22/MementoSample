package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.utils.AppUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IntroductionSlide extends Fragment {

    public static final String TAG = IntroductionSlide.class.getSimpleName();

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

        ImageView mSpeakingHead;
        ImageView mSmartphone;
        TextView mDescription;

        @Override
        public void transformPage(View page, float position) {
            /*
            * // Get the page index from the tag. This makes
        // it possible to know which page index you're
        // currently transforming - and that can be used
        // to make some important performance improvements.
        int pagePosition = (int) page.getTag();

        // Here you can do all kinds of stuff, like get the
        // width of the page and perform calculations based
        // on how far the user has swiped the page.
        int pageWidth = page.getWidth();
        float pageWidthTimesPosition = pageWidth * position;
        float absPosition = Math.abs(position);

        // Now it's time for the effects
        if (position <= -1.0f || position >= 1.0f) {

            // The page is not visible. This is a good place to stop
            // any potential work / animations you may have running.

        } else if (position == 0.0f) {

            // The page is selected. This is a good time to reset Views
            // after animations as you can't always count on the PageTransformer
            // callbacks to match up perfectly.

        } else {

            // The page is currently being scrolled / swiped. This is
            // a good place to show animations that react to the user's
            // swiping as it provides a good user experience.

            // Let's start by animating the title.
            // We want it to fade as it scrolls out
            View title = page.findViewById(R.id.title);
            title.setAlpha(1.0f - absPosition);

            // Now the description. We also want this one to
            // fade, but the animation should also slowly move
            // down and out of the screen
            View description = page.findViewById(R.id.description);
            description.setTranslationY(-pageWidthTimesPosition / 2f);
            description.setAlpha(1.0f - absPosition);

            // Now, we want the image to move to the right,
            // i.e. in the opposite direction of the rest of the
            // content while fading out
            View computer = page.findViewById(R.id.computer);

            // We're attempting to create an effect for a View
            // specific to one of the pages in our ViewPager.
            // In other words, we need to check that we're on
            // the correct page and that the View in question
            // isn't null.
            if (pagePosition == 0 && computer != null) {
                computer.setAlpha(1.0f - absPosition);
                computer.setTranslationX(-pageWidthTimesPosition * 1.5f);
            }

            // Finally, it can be useful to know the direction
            // of the user's swipe - if we're entering or exiting.
            // This is quite simple:
            if (position < 0) {
                // Create your out animation here
            } else {
                // Create your in animation here
            }
        }
            * */

            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();

            if (page.getTag().equals(AppUtilities.Constants.INTRODUCTION_VALUE)) {
                mSpeakingHead = (ImageView) page.findViewById(R.id.iv_speaking);
                mSmartphone = (ImageView) page.findViewById(R.id.iv_smartphone);
                mDescription = (TextView) page.findViewById(R.id.tv_description_introduction);
                mSpeakingHead.setTranslationX(position * (2 * pageWidth));
                mSmartphone.setTranslationX(position * (2 * pageWidth));
                mDescription.setTranslationX(position * (2 * pageWidth));
                /*if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setAlpha(1);
                } else if (position <= 1) { // [-1,1]
                    mSpeakingHead = (ImageView) page.findViewById(R.id.iv_speaking);
                    mSmartphone = (ImageView) page.findViewById(R.id.iv_smartphone);
                    mDescription = (TextView) page.findViewById(R.id.tv_description_introduction);
                    mSpeakingHead.setTranslationX(position * (pageWidth / 2));
                    mSmartphone.setTranslationX(position * (pageWidth / 2));
                    mDescription.setTranslationX(position * (pageWidth / 2));
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.setAlpha(1);
                }*/
            }
        }
    }
}
