package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;

public class FirstSlide extends Fragment {

    public static FirstSlide newInstance() {
        FirstSlide fragment = new FirstSlide();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_slide, container, false);

        return rootView;
    }

}
