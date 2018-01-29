package com.bytebuilding.memento.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EmptyContentFragment extends Fragment {

    public static final String TAG = "EmptyContentFragment";

    private Unbinder mUnbinder = null;

    public static EmptyContentFragment newInstance() {
        EmptyContentFragment fragment = new EmptyContentFragment();

        return fragment;
    }

    public static EmptyContentFragment newInstance(Bundle args) {
        EmptyContentFragment fragment = new EmptyContentFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_empty_content, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }
}
