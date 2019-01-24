package com.franlopez.androidcertification;

import android.app.Application;
import android.content.Context;

import com.franlopez.androidcertification.api.ApiService;

public class CustomApplication extends Application {

    private static Context sInstance = null;

    public static Context getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        ApiService.getRetrofitInstance();
    }
}
