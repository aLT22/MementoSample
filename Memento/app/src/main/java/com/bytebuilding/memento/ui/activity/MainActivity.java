package com.bytebuilding.memento.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bytebuilding.memento.R;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.mvp.presenter.MainViewPresenter;
import com.bytebuilding.memento.mvp.view.MainView;
import com.bytebuilding.memento.ui.fragment.content.EmptyContentFragment;
import com.bytebuilding.memento.ui.fragment.content.MementoListFragment;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    /*@BindView(R.id.spc_settings)
    SettingsPanelConstraint mSettingsPanel;*/

    @Inject
    SharedPreferences mPreferences;

    @Inject
    SharedPreferences.Editor mPreferencesEditor;

    @InjectPresenter
    MainViewPresenter mPresenter;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private Unbinder mUnbinder = null;

    private int mNativeFabCoordX = 0;
    private int mNativeFabCoordY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MementoApplication.getAppComponent().inject(this);

        startTutorialScreen();

        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        mNativeFabCoordX = mFab.getWidth();
        mNativeFabCoordY = mFab.getHeight();

        mPresenter.setFragment();
    }

    @Override
    protected void onDestroy() {
        mDisposable.dispose();
        mUnbinder.unbind();
        mPresenter.onDestroy();

        super.onDestroy();
    }

    private synchronized void startTutorialScreen() {
        Thread t = new Thread(() -> {
            boolean isFirstStart = mPreferences.getBoolean(AppUtilities.Constants.KEY_APP_FIRST_START, true);

            if (isFirstStart) {
                final Intent i = new Intent(MainActivity.this, OnboardingActivity.class);

                runOnUiThread(() -> {
                    startActivity(i);
                    finish();
                });

                mPreferencesEditor.putBoolean(AppUtilities.Constants.KEY_APP_FIRST_START, false);
                mPreferencesEditor.apply();
            }
        }
        );
        t.start();
    }

    private void showAppropriateFragment(Fragment fragmentInstance, String tag) {
        int fragmentsInBackStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsInBackStack == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_fragment_container, fragmentInstance, tag)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_fragment_container, fragmentInstance, tag)
                    .commit();
        }
    }

    @OnClick(R.id.fab)
    public void onFabCLick() {
        //MementoApplication.bus().send(new UiEvents.ShowSettingsEvent());
        MementoApplication.bus().send(new UiEvents.AnimateFabEvent());
    }

    @Override
    public void showMementosScreen() {
        showAppropriateFragment(MementoListFragment.newInstance(), MementoListFragment.TAG);
    }

    @Override
    public void showEmptyScreen() {
        showAppropriateFragment(EmptyContentFragment.newInstance(), EmptyContentFragment.TAG);
    }

    @Override
    public void startRecording() {
        ObjectAnimator fabAnimationUp = ObjectAnimator.ofFloat(mFab, "translationY", mNativeFabCoordY, mNativeFabCoordY - 100);
        ObjectAnimator fabAnimationDown = ObjectAnimator.ofFloat(mFab, "translationY", mNativeFabCoordY - 100, mNativeFabCoordY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(fabAnimationUp).before(fabAnimationDown);
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }

    @Override
    public void pauseRecording() {

    }

    @Override
    public void stopRecording() {

    }

    @Override
    public void showSettings() {
        //mSettingsPanel.slide();
    }
}
