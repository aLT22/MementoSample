package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;

public class PressOnceMoreSlide extends Fragment {

    public static final String TAG = PressOnceMoreSlide.class.getSimpleName();

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

        return rootView;
    }

}
