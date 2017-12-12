package ru.relex.animatabletextview.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Turkin A. on 12.12.2017.
 */

public class DisplayUtils {

    public static final String TAG = DisplayUtils.class.getSimpleName();

    private DisplayUtils(){
    }

    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public static int dp2px(float dp) {
        return Math.round(dp * getDisplayMetrics().density);
    }

    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

}
