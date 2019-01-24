package com.franlopez.androidcertification.api;

public class ServiceFactory {
    public static GithubService createGithubService() {
        return ApiService.getRetrofitInstance().create(GithubService.class);
    }
}
