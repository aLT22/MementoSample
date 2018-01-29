package com.bytebuilding.memento.ui.fragment.intro;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.events.ui.UiEvents;
import com.bytebuilding.memento.ui.animation.intro.IntroAnimator;
import com.bytebuilding.memento.ui.animation.intro.JustPressButtonSlideAnimator;
import com.bytebuilding.memento.utils.AppUtilities;
import com.bytebuilding.memento.utils.MementoApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class JustPressButtonSlide extends Fragment {

    public static final String TAG = "JustPressButtonSlide";

    @BindView(R.id.iv_arrow_down_4dp)
    ImageView mArrowDown4;

    @BindView(R.id.iv_arrow_down_8dp)
    ImageView mArrowDown8;

    @BindView(R.id.iv_arrow_down_16dp)
    ImageView mArrowDown16;

    @BindView(R.id.iv_arrow_down_24dp)
    ImageView mArrowDown24;

    @BindView(R.id.iv_arrow_down_32dp)
    ImageView mArrowDown32;

    @BindView(R.id.iv_arrow_down_48dp)
    ImageView mArrowDown48;

    @BindView(R.id.iv_arrow_down_56dp)
    ImageView mArrowDown56;

    @BindView(R.id.tv_description_just_press_button)
    TextView mDescription;

    private Unbinder mUnbinder;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private ImageView[] mImageArrows = new ImageView[7];

    public static JustPressButtonSlide newInstance() {
        JustPressButtonSlide fragment = new JustPressButtonSlide();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_just_press_button_slide, container, false);

        rootView.setTag(AppUtilities.Constants.JUST_PRESS_BUTTON_VALUE);

        mUnbinder = ButterKnife.bind(this, rootView);

        mImageArrows[0] = mArrowDown4;
        mImageArrows[1] = mArrowDown8;
        mImageArrows[2] = mArrowDown16;
        mImageArrows[3] = mArrowDown24;
        mImageArrows[4] = mArrowDown32;
        mImageArrows[5] = mArrowDown48;
        mImageArrows[6] = mArrowDown56;

        catcherEvents();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    private void catcherEvents() {
        mDisposable
                .add(MementoApplication.bus()
                        .observable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                if (o instanceof UiEvents.ChangeArrowColorEvent) {
                                    if (UiEvents.ChangeArrowColorEvent.sArrowIndex == 0) {
                                        mImageArrows[UiEvents.ChangeArrowColorEvent.sArrowIndex]
                                                .setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                                        mImageArrows[6]
                                                .setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorGray));
                                    } else {
                                        mImageArrows[UiEvents.ChangeArrowColorEvent.sArrowIndex]
                                                .setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                                        mImageArrows[UiEvents.ChangeArrowColorEvent.sArrowIndex - 1]
                                                .setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorGray));
                                    }
                                }
                            }
                        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mDisposable.dispose();
    }

    public class JustPressButtonSlidePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            IntroAnimator pagerAnimator = new JustPressButtonSlideAnimator();
            pagerAnimator.animateSlide(page, position, R.id.tv_description_just_press_button, AppUtilities.Constants.JUST_PRESS_BUTTON_VALUE);
        }
    }
}
