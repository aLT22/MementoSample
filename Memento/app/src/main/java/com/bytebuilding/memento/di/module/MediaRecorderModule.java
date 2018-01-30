package com.bytebuilding.memento.di.module;

import android.media.MediaRecorder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Turkin A. on 30.01.2018.
 */

@Module
public class MediaRecorderModule {

    @Singleton
    @Provides
    MediaRecorder provideMediaRecorder() {
        MediaRecorder mAudioRecorder = new MediaRecorder();

        mAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        return mAudioRecorder;
    }

}
