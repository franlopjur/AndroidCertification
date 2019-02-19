package com.franlopez.androidcertification.commons;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewHelper {
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
