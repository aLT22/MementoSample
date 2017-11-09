package com.bytebuilding.memento.ui.fragment.intro;


import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bytebuilding.memento.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PressOnceMoreSlide extends Fragment {

    public static final String TAG = PressOnceMoreSlide.class.getSimpleName();

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.iv_recording_animated)
    ImageView mRecordingIcon;

    private Unbinder mUnbinder = null;

    public static PressOnceMoreSlide newInstance() {
        PressOnceMoreSlide fragment = new PressOnceMoreSlide();

        return fragment;
    }

    public static PressOnceMoreSlide newInstance(Bundle args) {
        PressOnceMoreSlide fragment = new PressOnceMoreSlide();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_press_once_more_slide, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        Drawable recordingDrawable = mRecordingIcon.getDrawable();
        if (recordingDrawable != null && recordingDrawable instanceof Animatable) {
            ((Animatable) recordingDrawable).start();
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }
}
