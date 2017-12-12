package com.alexeyturkin.mementosettingspanel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.alexeyturkin.mementosettingspanel.R;
import com.alexeyturkin.mementosettingspanel.logic.SlidingPanelAnimator;
import com.alexeyturkin.mementosettingspanel.utils.Utilities;

/**
 * Created by Turkin A. on 13.11.2017.
 */

public class SlidingPanelConstraint extends ConstraintLayout {

    //Attributes
    private int attrSpeed = Utilities.DEFAULT_ANIMATION_SPEED;
    private String attrInterpolator = Utilities.DEFAULT_INTERPOLATOR;
    private String attrDirection = Utilities.DEFAULT_DIRECTION;

    //Variables
    private boolean isOnTheScreen = false;
    private Animation fadeAnimation = null;

    public SlidingPanelConstraint(Context context) {
        super(context);
    }

    public SlidingPanelConstraint(Context context, AttributeSet attrs) {
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
        if (isOnTheScreen) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            setClickable(true);
        } else {
            this.clearFocus();
        }
        SlidingPanelAnimator animator = new SlidingPanelAnimator();
        animator.playAnimation(this,
                attrSpeed, isOnTheScreen, fadeAnimation, attrDirection, attrInterpolator);
    }
}
