package ru.relex.animatabletextview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Turkin A. on 15.12.2017.
 */

public abstract class AnimatableTextView extends TextView {


    public AnimatableTextView(Context context) {
        this(context, null);
    }

    public AnimatableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setProgress(float progress);

    public abstract void animateText(CharSequence text);
}
