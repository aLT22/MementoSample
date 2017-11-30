package com.bytebuilding.memento.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Turkin A. on 09.11.17.
 */

public class AppUtilities {

    public static final String TAG = AppUtilities.class.getSimpleName();

    private AppUtilities() {
    }

    public static boolean isFolderEmpty() {
        File directory = new File(Environment.getExternalStorageDirectory() + File.pathSeparator + Constants.APP_NAME);
        File[] contents = directory.listFiles();
        if (contents == null) {
            return true;
        } else if (contents.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static class Constants {

        public static final String TAG = Constants.class.getSimpleName();

        private Constants() {
        }

        public static final String APP_NAME = "Memento";

        //Shared preferences constants
        public static final String SHARED_PREFERENCES_NAME = "MementoPreferences";
        public static final String KEY_APP_FIRST_START = "firstStart";

        //Constants for onboarding ViewPager
        public static final int PAGES_COUNT = 4;
        public static final int INTRODUCTION_SLIDE_PAGE = 0;
        public static final int JUST_PRESS_BUTTON_SLIDE_PAGE = 1;
        public static final int PRESS_ONCE_MORE_SLIDE_PAGE = 2;
        public static final int STOP_RECORDING_SLIDE_PAGE = 3;

        //Keys for slides' tags and values
        public static final int INTRODUCTION_VALUE = 0;
        public static final int JUST_PRESS_BUTTON_VALUE = 1;
        public static final int PRESS_ONCE_MORE_VALUE = 2;
        public static final int STOP_RECORDING_VALUE = 3;

    }

}
