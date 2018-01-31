package com.bytebuilding.memento.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bytebuilding.memento.R;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.mvp.presenter.MainViewPresenter;
import com.bytebuilding.memento.mvp.view.MainView;
import com.bytebuilding.memento.ui.animation.fab.PounceAnimationFab;
import com.bytebuilding.memento.ui.fragment.content.EmptyContentFragment;
import com.bytebuilding.memento.ui.fragment.content.MementoListFragment;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
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

    private PounceAnimationFab mPounceAnimationFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MementoApplication.getAppComponent().inject(this);

        startTutorialScreen();

        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        mPounceAnimationFab = new PounceAnimationFab();

        mNativeFabCoordX = mFab.getWidth();
        mNativeFabCoordY = mFab.getHeight();

        getLifecycle().addObserver(mPresenter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                        AppUtilities.Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case AppUtilities.Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast
                            .makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast
                            .makeText(this, R.string.must_allow_permissions, Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
            }
            // Add additional cases for other permissions you may have asked for
        }
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
        MementoApplication.bus().send(new UiEvents.AnimateFabEvent());
    }

    @OnLongClick(R.id.fab)
    public boolean onFabLongClickListener() {
        MementoApplication.bus().send(new UiEvents.StopRecordingFabEvent());

        return true;
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
        mPounceAnimationFab.pounceAnimation(mFab,
                "translationY",
                R.drawable.ic_recording_24dp,
                250,
                mNativeFabCoordY,
                mNativeFabCoordY - 300);
    }

    @Override
    public void pauseRecording() {
        mPounceAnimationFab.pounceAnimation(mFab,
                "translationY",
                R.drawable.ic_pause_white_24dp,
                250,
                mNativeFabCoordY,
                mNativeFabCoordY - 300);
    }

    @Override
    public void stopRecording() {
        mPounceAnimationFab.pounceAnimation(mFab,
                "translationY",
                R.drawable.ic_add_24dp,
                250,
                mNativeFabCoordY,
                mNativeFabCoordY - 300);
    }

    @Override
    public void showSettings() {

    }
}
