package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class StopRecordingSlide extends Fragment {

    public static final String TAG = StopRecordingSlide.class.getCanonicalName();

    private Unbinder mUnbinder = null;

    public static StopRecordingSlide newInstance() {
        StopRecordingSlide fragment = new StopRecordingSlide();

        return fragment;
    }

    public static StopRecordingSlide newInstance(Bundle args) {
        StopRecordingSlide fragment = new StopRecordingSlide();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_recording_slide, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }
}
