package com.bytebuilding.memento.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bytebuilding.memento.R;
import com.bytebuilding.memento.mvp.view.MementoListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MementoListFragment extends MvpAppCompatFragment implements MementoListView {

    public static final String TAG = MementoListFragment.class.getSimpleName();

    @BindView(R.id.rv_memento_list)
    RecyclerView mList;

    @BindView(R.id.pb_loading)
    ProgressBar mLoading;

    private Unbinder mUnbinder = null;

    public static MementoListFragment newInstance() {
        MementoListFragment fragment = new MementoListFragment();

        return fragment;
    }

    public static MementoListFragment newInstance(Bundle args) {
        MementoListFragment fragment = new MementoListFragment();

        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memento_list, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    @Override
    public void showMementosList() {

    }

    @Override
    public void showProgressBar() {
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mLoading.setVisibility(View.GONE);
    }
}
