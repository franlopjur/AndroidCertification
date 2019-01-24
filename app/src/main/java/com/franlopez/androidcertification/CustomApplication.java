package com.franlopez.androidcertification;

import android.app.Application;

import com.franlopez.androidcertification.api.ApiService;

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ApiService.getRetrofitInstance();
    }
}
