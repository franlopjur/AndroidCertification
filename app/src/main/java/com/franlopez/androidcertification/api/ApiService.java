package com.franlopez.androidcertification.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.franlopez.androidcertification.commons.Constants.BASE_URL;

public class ApiService {
    private static Retrofit sRetrofitInstance = null;
    private static OkHttpClient sClient = null;

    private ApiService() {}

    public static synchronized Retrofit getRetrofitInstance() {
        if (sRetrofitInstance == null) {
            sRetrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClientInstance())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofitInstance;
    }

    private static synchronized OkHttpClient getClientInstance() {
        if (sClient == null) {
            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BASIC);
            sClient = new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build();
        }
        return sClient;
    }
}
