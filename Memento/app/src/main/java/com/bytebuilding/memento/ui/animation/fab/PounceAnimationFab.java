package com.bytebuilding.memento.ui.animation.fab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Turkin A. on 30.01.2018.
 */

public class PounceAnimationFab {

    public static final String TAG = "PounceAnimationFab";

    public void pounceAnimation(FloatingActionButton targetView,
                                String propertyName,
                                @DrawableRes int iconRes,
                                int duration,
                                int valueUp,
                                int valueDown) {
        ObjectAnimator fabAnimationUp = ObjectAnimator.ofFloat(targetView, propertyName, valueUp, valueDown);
        ObjectAnimator fabAnimationDown = ObjectAnimator.ofFloat(targetView, propertyName, valueDown, valueUp);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                targetView.setImageResource(iconRes);
                targetView.setEnabled(true);

                /*// get the center for the clipping circle
                int cx = (mStartRecordingPanel.getRight() + mStartRecordingPanel.getLeft()) / 2;
                //int cy = (mFab.getTop() - mFab.getBottom()) / 2;
                int cy = (mStartRecordingPanel.getTop() + mStartRecordingPanel.getBottom()) / 2;

                // get the final radius for the clipping circle
                int finalRadius = Math.max(mFab.getWidth(), mStartRecordingPanel.getWidth());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(mStartRecordingPanel, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                mStartRecordingPanel.setVisibility(View.VISIBLE);
                anim.setDuration(300);
                anim.start();*/
            }
        });

        animatorSet.play(fabAnimationUp).before(fabAnimationDown);
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();

        targetView.setEnabled(false);
    }

}
