package com.franlopez.androidcertification.commons;

import android.text.TextUtils;

public class ValidationUtils {
    public static boolean containsText(String text, String textContained) {
        return !TextUtils.isEmpty(text) &&
                !TextUtils.isEmpty(textContained) &&
                text.toLowerCase().contains(textContained.toLowerCase());
    }
}
