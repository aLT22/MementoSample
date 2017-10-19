package com.bytebuilding.memento.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLanguageFragment extends Fragment {


    public ChooseLanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_language, container, false);
    }

}
