package com.franlopez.androidcertification.data;

public interface RepositoryListener<T> {
    void onSuccess(T response);
    void onError(String errorMessage);
}
