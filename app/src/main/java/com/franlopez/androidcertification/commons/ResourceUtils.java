package com.franlopez.androidcertification.commons;

import android.support.annotation.StringRes;

import com.franlopez.androidcertification.CustomApplication;

public class ResourceUtils {
    public static String getString(@StringRes int resId) {
        return CustomApplication.getInstance().getString(resId);
    }

    public static String getStringFormat(@StringRes int resId, Object... args) {
        return CustomApplication.getInstance().getString(resId, args);
    }
}
