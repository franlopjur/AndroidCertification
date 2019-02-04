package com.franlopez.androidcertification.ui.main;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.franlopez.androidcertification.data.GithubRepoApiDataSource;
import com.franlopez.androidcertification.data.GithubRepoCacheDataSource;
import com.franlopez.androidcertification.data.RepositoryListener;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

import java.util.List;

public class GithubRepoBoundaryCallback extends PagedList.BoundaryCallback<GithubRepoDomain> {

    private boolean isRequesting;
    private int lastPageRequestedNumber = 1;
    private String query;
    private GithubRepoCacheDataSource repoCacheDataSource;
    private GithubRepoApiDataSource repoApiDataSource;

    public GithubRepoBoundaryCallback(String query, GithubRepoApiDataSource repoApiDataSource,
                                      GithubRepoCacheDataSource repoCacheDataSource) {
        this.query = query;
        this.repoApiDataSource = repoApiDataSource;
        this.repoCacheDataSource = repoCacheDataSource;
    }

    public void setLastPageRequestedNumber(int lastPageRequestedNumber) {
        this.lastPageRequestedNumber = lastPageRequestedNumber;
    }

    public int getLastPageRequestedNumber() {
        return lastPageRequestedNumber;
    }

    public void resetLastPageRequestedNumber() {
        this.lastPageRequestedNumber = 1;
    }

    public void increaseLastPageRequestedNumber() {
        this.lastPageRequestedNumber++;
    }

    private void requestNewDataAndSave(String query) {
        isRequesting = true;
        repoApiDataSource.getGithubRepos(query, getLastPageRequestedNumber(),
                                         new RepositoryListener<List<GithubRepoDomain>>() {
                                             @Override
                                             public void onSuccess(List<GithubRepoDomain> response) {
                                                 isRequesting = false;
                                                 increaseLastPageRequestedNumber();
                                                 repoCacheDataSource.insert(response);
                                             }

                                             @Override
                                             public void onError(String errorMessage) {
                                                 isRequesting = false;
                                                 //errorMessageLiveData.setValue(errorMessage);
                                             }
                                         });
    }

    @Override
    public void onZeroItemsLoaded() {
        requestNewDataAndSave(query);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull GithubRepoDomain itemAtEnd) {
        requestNewDataAndSave(query);
    }
}
