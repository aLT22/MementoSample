package ru.relex.animatabletextview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.util.AttributeSet;

import ru.relex.animatabletextview.R;
import ru.relex.animatabletextview.utils.DisplayUtils;

/**
 * Created by Turkin A. on 12.12.2017.
 */

public class ColorAnimatableTextView extends AnimatableTextView {

    public static final String TAG = ColorAnimatableTextView.class.getSimpleName();

    private Matrix mMatrix;
    private float mTranslate;
    private float mColorSpeed;
    private float mColorSpace;
    private LinearGradient mLinearGradient;

    private int[] mColors = {0xFFD2D2D2, 0xFFE7E7E7};

    public ColorAnimatableTextView(Context context) {
        this(context, null);
    }

    public ColorAnimatableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorAnimatableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    public void setProgress(float progress) {
    }

    @Override
    public void animateText(CharSequence text) {
        setText(text);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorAnimatableTextView);
        mColorSpace = typedArray.getDimension(R.styleable.ColorAnimatableTextView_colorSpace, DisplayUtils.dp2px(150));
        mColorSpeed = typedArray.getDimension(R.styleable.ColorAnimatableTextView_colorSpeed, DisplayUtils.dp2px(5));
        typedArray.recycle();

        mMatrix = new Matrix();
        initPaint();
    }

    private void initPaint() {
        mLinearGradient = new LinearGradient(0, 0, mColorSpace, 0, mColors, null, Shader.TileMode.MIRROR);
        getPaint().setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMatrix == null) {
            mMatrix = new Matrix();
        }
        mTranslate += mColorSpeed;
        mMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        super.onDraw(canvas);
        postInvalidateDelayed(100);
    }
}
