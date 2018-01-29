package com.bytebuilding.memento.ui.animation.intro;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.utils.AppUtilities;

/**
 * Created by Turkin A. on 04.12.2017.
 */

public class PressOnceMoreSlideAnimator extends IntroAnimator {

    public static final String TAG = "PressOnceMoreSlideAnimator";

    @Override
    public void animateSlide(View page, float position, @IdRes int resId, int animationValue) {
        super.animateSlide(page, position, resId, animationValue);
    }
}
