package com.bytebuilding.memento.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;

public class ChooseLanguageFragment extends Fragment {

    public static ChooseLanguageFragment newInstance() {
        ChooseLanguageFragment fragment = new ChooseLanguageFragment();

        return fragment;
    }

    public static ChooseLanguageFragment newInstance(Bundle bundle) {
        ChooseLanguageFragment fragment = new ChooseLanguageFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_language, container, false);

        return rootView;
    }

}
