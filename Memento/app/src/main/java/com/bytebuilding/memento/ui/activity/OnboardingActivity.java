package com.bytebuilding.memento.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bytebuilding.memento.R;
import com.bytebuilding.memento.mvp.presenter.OnboardingViewPresenter;
import com.bytebuilding.memento.mvp.view.OnboardingView;
import com.bytebuilding.memento.ui.adapter.OnboardingViewPagerAdapter;
import com.bytebuilding.memento.ui.fragment.intro.IntroductionSlide;
import com.bytebuilding.memento.ui.fragment.intro.JustPressButtonSlide;
import com.bytebuilding.memento.ui.fragment.intro.PressOnceMoreSlide;
import com.bytebuilding.memento.ui.fragment.intro.StopRecordingSlide;
import com.bytebuilding.memento.utils.MementoApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OnboardingActivity extends AppCompatActivity implements OnboardingView {

    public static final String TAG = "OnboardingActivity";

    @BindView(R.id.vp_onboarding)
    ViewPager mOnboardingPager;

    @BindView(R.id.iv_onboarding_status)
    ImageView mNextSlide;

    @Inject
    SharedPreferences.Editor mPreferencesEditor;

    @InjectPresenter
    OnboardingViewPresenter mPresenter;

    private Unbinder mUnbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MementoApplication.getAppComponent().inject(this);

        setContentView(R.layout.activity_onboarding);

        mUnbinder = ButterKnife.bind(this);

        setTutorialSlides();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();

        super.onDestroy();
    }

    private void setTutorialSlides() {
        mOnboardingPager.setPageTransformer(true,
                IntroductionSlide.newInstance().new IntroductionSlidesPageTransformer());
        OnboardingViewPagerAdapter viewPagerAdapter = new OnboardingViewPagerAdapter(getSupportFragmentManager());
        mOnboardingPager.setAdapter(viewPagerAdapter);
        mOnboardingPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        mOnboardingPager.setPageTransformer(true,
                                IntroductionSlide.newInstance().new IntroductionSlidesPageTransformer());
                        break;

                    case 1:
                        mOnboardingPager.setPageTransformer(true,
                                JustPressButtonSlide.newInstance().new JustPressButtonSlidePageTransformer());
                        break;

                    case 2:
                        mOnboardingPager.setPageTransformer(true,
                                PressOnceMoreSlide.newInstance().new PressOnceMoreSlidePageTransformer());
                        break;

                    case 3:
                        mOnboardingPager.setPageTransformer(true,
                                StopRecordingSlide.newInstance().new StopRecordingSlidePageTransformer());
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mOnboardingPager.setPageTransformer(true,
                                IntroductionSlide.newInstance().new IntroductionSlidesPageTransformer());
                        break;

                    case 1:
                        mOnboardingPager.setPageTransformer(true,
                                JustPressButtonSlide.newInstance().new JustPressButtonSlidePageTransformer());
                        break;

                    case 2:
                        mOnboardingPager.setPageTransformer(true,
                                PressOnceMoreSlide.newInstance().new PressOnceMoreSlidePageTransformer());
                        break;

                    case 3:
                        mOnboardingPager.setPageTransformer(true,
                                StopRecordingSlide.newInstance().new StopRecordingSlidePageTransformer());
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void startMainActivity() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
        this.finish();
    }

    @OnClick(R.id.iv_onboarding_status)
    public void onNextSlideClick() {
        if (mOnboardingPager.getCurrentItem() != mOnboardingPager.getAdapter().getCount() - 1) {
            showNextSlide(mOnboardingPager.getCurrentItem() + 1);
        } else {
            changeButtonStatus();
        }
    }

    @Override
    public void showMainView() {
        startMainActivity();
    }

    @Override
    public void showNextSlide(int pageNumber) {
        mOnboardingPager.setCurrentItem(pageNumber, true);
    }

    @Override
    public void changeButtonStatus() {
        //Button animation

        //Open main screen of application
        showMainView();
    }
}
