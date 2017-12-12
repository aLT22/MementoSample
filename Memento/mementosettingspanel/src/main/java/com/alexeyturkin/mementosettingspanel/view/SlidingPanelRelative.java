package com.alexeyturkin.mementosettingspanel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.alexeyturkin.mementosettingspanel.R;
import com.alexeyturkin.mementosettingspanel.logic.SlidingPanelAnimator;
import com.alexeyturkin.mementosettingspanel.utils.Utilities;

/**
 * Created by Turkin A. on 13.11.2017.
 */

public class SlidingPanelRelative extends RelativeLayout {

    //Attributes
    private int attrSpeed = Utilities.DEFAULT_ANIMATION_SPEED;
    private String attrInterpolator = Utilities.DEFAULT_INTERPOLATOR;
    private String attrDirection = Utilities.DEFAULT_DIRECTION;

    //Variables
    private boolean isOnTheScreen = false;
    private Animation fadeAnimation = null;

    public SlidingPanelRelative(Context context) {
        super(context);
    }

    public SlidingPanelRelative(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SlidingPanel,
                0, 0);

        attrSpeed = a.getInt(R.styleable.SlidingPanel_speed, Utilities.DEFAULT_ANIMATION_SPEED);

        a.recycle();

        fadeAnimation = AnimationUtils.loadAnimation(context, R.anim.fade);
    }

    public void slide() {
        isOnTheScreen = !isOnTheScreen;
        SlidingPanelAnimator animator = new SlidingPanelAnimator();
        animator.playAnimation(this,
                attrSpeed, isOnTheScreen, fadeAnimation, attrDirection, attrInterpolator);
    }
}
