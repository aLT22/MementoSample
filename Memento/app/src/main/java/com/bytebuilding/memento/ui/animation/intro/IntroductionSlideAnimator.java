package com.bytebuilding.memento.ui.animation.intro;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.utils.AppUtilities;

/**
 * Created by Turkin A. on 04.12.2017.
 */

public class IntroductionSlideAnimator extends IntroAnimator {

    public static final String TAG = "IntroductionSlideAnimator";

    @Override
    public void animateSlide(View page, float position, @IdRes int resId, int animationValue) {

        ImageView mSpeakingHead = (ImageView) page.findViewById(R.id.iv_speaking);
        ImageView mSmartphone = (ImageView) page.findViewById(R.id.iv_smartphone);
        TextView mDescription = (TextView) page.findViewById(R.id.tv_description_introduction);

        // Get the page index from the tag. This makes
        // it possible to know which page index you're
        // currently transforming - and that can be used
        // to make some important performance improvements.
        int pagePosition = (int) page.getTag();

        // Here you can do all kinds of stuff, like get the
        // width of the page and perform calculations based
        // on how far the user has swiped the page.
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        float pageWidthTimesPosition = pageWidth * position;
        float absPosition = Math.abs(position);

        if (pagePosition == animationValue) {
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


                // Now the description. We also want this one to
                // fade, but the animation should also slowly move
                // down and out of the screen
                mDescription.setTranslationY(-pageWidthTimesPosition * 2f);
                //mDescription.setTranslationX(-pageWidthTimesPosition);
                mDescription.setAlpha(1.0f - absPosition);

                // Now, we want the image to move to the right,
                // i.e. in the opposite direction of the rest of the
                // content while fading out

                // We're attempting to create an effect for a View
                // specific to one of the pages in our ViewPager.
                // In other words, we need to check that we're on
                // the correct page and that the View in question
                // isn't null.
                mSpeakingHead.setAlpha(1.0f - 2 * absPosition);
                mSmartphone.setAlpha(1.0f - 2 * absPosition);
                mSpeakingHead.setTranslationY(pageWidthTimesPosition * 1.5f);
                mSpeakingHead.setTranslationX(-pageWidthTimesPosition);
                mSmartphone.setTranslationY(pageWidthTimesPosition * 1.5f);
                mSmartphone.setTranslationX(-pageWidthTimesPosition);

                // Finally, it can be useful to know the direction
                // of the user's swipe - if we're entering or exiting.
                // This is quite simple:
                if (position < 0) {
                    // Create your out animation here
                } else {
                    // Create your in animation here
                }
            }
        }
    }
}
