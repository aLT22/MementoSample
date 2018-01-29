package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.ui.animation.intro.IntroAnimator;
import com.bytebuilding.memento.ui.animation.intro.StopRecordingSlideAnimator;
import com.bytebuilding.memento.utils.AppUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class StopRecordingSlide extends Fragment {

    public static final String TAG = "StopRecordingSlide";

    @BindView(R.id.tv_description_stop_recording)
    TextView mDescription;

    private Unbinder mUnbinder = null;

    public static StopRecordingSlide newInstance() {
        StopRecordingSlide fragment = new StopRecordingSlide();

        return fragment;
    }

    public static StopRecordingSlide newInstance(Bundle args) {
        StopRecordingSlide fragment = new StopRecordingSlide();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_recording_slide, container, false);

        rootView.setTag(AppUtilities.Constants.STOP_RECORDING_VALUE);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    public class StopRecordingSlidePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            IntroAnimator pagerAnimator = new StopRecordingSlideAnimator();
            pagerAnimator.animateSlide(page, position, R.id.tv_description_stop_recording, AppUtilities.Constants.STOP_RECORDING_VALUE);
        }
    }
}
