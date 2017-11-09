package com.bytebuilding.memento.utils;

/**
 * Created by Turkin A. on 09.11.17.
 */

public class AppUtilities {

    public static final String TAG = AppUtilities.class.getSimpleName();

    private AppUtilities() {
    }

    public static class Constants {

        public static final String TAG = Constants.class.getSimpleName();

        //Shared preferences constants
        public static final String SHARED_PREFERENCES_NAME = "MementoPreferences";
        public static final String KEY_APP_FIRST_START = "firstStart";
        public static final String KEY_APP_LANGUAGE = "appLanguage";

        //Language constants
        public static final String RU = "ru";
        public static final String EN = "en";

    }

}
