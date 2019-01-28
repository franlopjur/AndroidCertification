package com.franlopez.androidcertification;

import android.app.Application;
import android.content.Context;

import com.franlopez.androidcertification.api.ApiService;

import java.lang.ref.WeakReference;

public class CustomApplication extends Application {

    private static WeakReference<Context> sInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        ApiService.getRetrofitInstance();
    }

    public static Context getInstance() {
        return sInstance.get();
    }

    private static void setContext(Context context) {
        sInstance = new WeakReference<>(context);
    }
}
