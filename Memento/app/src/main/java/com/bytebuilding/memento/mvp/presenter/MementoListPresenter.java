package com.bytebuilding.memento.mvp.presenter;

import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bytebuilding.memento.events.data.DataEvents;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.mvp.model.MementoModel;
import com.bytebuilding.memento.mvp.view.MementoListView;
import com.bytebuilding.memento.ui.adapter.MementoRecyclerAdapter;
import com.bytebuilding.memento.utils.MementoApplication;
import com.bytebuilding.memento.utils.MementoDiffUtilsCallback;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Turkin A. on 12.11.2017.
 */

@InjectViewState
public class MementoListPresenter extends MvpPresenter<MementoListView> {

    public static final String TAG = MementoListPresenter.class.getSimpleName();

    @Inject
    MementoModel mModel;

    @Inject
    MementoRecyclerAdapter mAdapter;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public MementoListPresenter() {
        MementoApplication.getAppComponent().inject(this);
        catcherEvents();
    }

    public void getMementosListFromModel() {
        getViewState().showProgressBar();

        mModel.setFakeMementos();
    }

    private void catcherEvents() {
        mDisposable.add(
                MementoApplication
                        .bus()
                        .observable()
                        .subscribeOn(Schedulers.io())
                        .subscribe(o -> {
                            if (o instanceof DataEvents.DataWasFetchedEvent) {
                                Observable
                                        .fromCallable(this::сhangesResult)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<DiffUtil.DiffResult>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(DiffUtil.DiffResult diffResult) {
                                                mAdapter.setList(mModel.getmMementos());
                                                diffResult.dispatchUpdatesTo(mAdapter);
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.e(TAG, "onError: " + e.getMessage());
                                                getViewState().hideProgressBar();
                                            }

                                            @Override
                                            public void onComplete() {
                                                getViewState().showMementosList();
                                                getViewState().hideProgressBar();
                                            }
                                        });
                            } else if (o instanceof UiEvents.ShowSettingsEvent) {
                                getViewState().showSettings();
                            }
                        })
        );
    }

    private DiffUtil.DiffResult сhangesResult() {
        MementoDiffUtilsCallback diffUtilsCallback =
                new MementoDiffUtilsCallback(mAdapter.getList(), mModel.getmMementos());
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilsCallback, true);

        return diffResult;
    }

    @Override
    public void onDestroy() {
        mModel.freeMemory();
        mDisposable.dispose();

        super.onDestroy();
    }
}
