package com.bytebuilding.memento.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

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

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    /*@BindView(R.id.spc_settings)
    SettingsPanelConstraint mSettingsPanel;*/

    @BindView(R.id.ll_bottom_bar)
    LinearLayout mStartRecordingPanel;

    @Inject
    SharedPreferences mPreferences;

    @Inject
    SharedPreferences.Editor mPreferencesEditor;

    @InjectPresenter
    MainViewPresenter mPresenter;

    private Unbinder mUnbinder = null;

    private int mNativeFabCoordX;
    private int mNativeFabCoordY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MementoApplication.getAppComponent().inject(this);

        startTutorialScreen();

        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        mNativeFabCoordX = mFab.getWidth();
        mNativeFabCoordY = mFab.getHeight();

        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();

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
        ObjectAnimator fabAnimationUp = ObjectAnimator.ofFloat(mFab, "translationY", mNativeFabCoordY, mNativeFabCoordY - 300);
        ObjectAnimator fabAnimationDown = ObjectAnimator.ofFloat(mFab, "translationY", mNativeFabCoordY - 300, mNativeFabCoordY);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mFab.setEnabled(true);

                /*// get the center for the clipping circle
                int cx = (mStartRecordingPanel.getRight() + mStartRecordingPanel.getLeft()) / 2;
                //int cy = (mFab.getTop() - mFab.getBottom()) / 2;
                int cy = (mStartRecordingPanel.getTop() + mStartRecordingPanel.getBottom()) / 2;

                // get the final radius for the clipping circle
                int finalRadius = Math.max(mFab.getWidth(), mStartRecordingPanel.getWidth());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(mStartRecordingPanel, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                mStartRecordingPanel.setVisibility(View.VISIBLE);
                anim.setDuration(300);
                anim.start();*/
            }
        });

        animatorSet.play(fabAnimationUp).before(fabAnimationDown);
        animatorSet.setDuration(250);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();

        mFab.setEnabled(false);
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
