package com.bytebuilding.memento.ui.fragment.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bytebuilding.memento.R;
import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.mvp.presenter.MementoListPresenter;
import com.bytebuilding.memento.mvp.view.MementoListView;
import com.bytebuilding.memento.ui.adapter.MementoRecyclerAdapter;
import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MementoListFragment extends MvpAppCompatFragment implements MementoListView {

    public static final String TAG = MementoListFragment.class.getSimpleName();

    @BindView(R.id.rv_memento_list)
    RecyclerView mList;

    @BindView(R.id.pb_loading)
    ProgressBar mLoading;

    @InjectPresenter
    MementoListPresenter mPresenter;

    @Inject
    MementoRecyclerAdapter mAdapter;

    @Inject
    MementoModel mModel;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MementoApplication.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memento_list, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        mPresenter.getMementosListFromModel();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    @Override
    public void showMementosList() {
        mList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (mAdapter != null) {
            mList.setAdapter(mAdapter);
            mList.setVerticalScrollBarEnabled(true);
        }
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
