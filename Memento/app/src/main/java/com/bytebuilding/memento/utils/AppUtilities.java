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

        //Shared preferences constants
        public static final String SHARED_PREFERENCES_NAME = "MementoPreferences";
        public static final String KEY_APP_FIRST_START = "firstStart";
        public static final String KEY_APP_LANGUAGE = "appLanguage";

        //Language constants
        public static final String RU = "ru";
        public static final String EN = "en";
        public static final String APP_NAME = "Memento";

    }

}
