package com.franlopez.androidcertification.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.franlopez.androidcertification.SingleLiveEvent;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

import java.util.List;

public class GithubRepository {
    //region Members
    private GithubRepoApiDataSource repoApiDataSource;
    private GithubRepoCacheDataSource repoCacheDataSource;

    private int lastPageRequestedNumber = 1;
    private String lastQueryInfoRequested = null;

    private MutableLiveData<List<GithubRepoDomain>> searchReposLiveData = new MutableLiveData<>();
    private SingleLiveEvent<String> errorMessageLiveData = new SingleLiveEvent<>();
    //endregion

    //region Constructors
    public GithubRepository() {
        this.repoApiDataSource = new GithubRepoApiDataSource();
        this.repoCacheDataSource = new GithubRepoCacheDataSource();
    }
    //endregion

    //region Getters and Setters
    public LiveData<List<GithubRepoDomain>> getSearchReposLiveData() {
        return searchReposLiveData;
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }

    private String getLastQueryInfoRequested() {
        return lastQueryInfoRequested;
    }

    private int getLastPageRequestedNumber() {
        return lastPageRequestedNumber;
    }

    private void setLastQueryInfoRequested(String query) {
        this.lastQueryInfoRequested = query;
    }

    //endregion

    //region Public Methods
    public LiveData<List<GithubRepoDomain>> searchRepos(String query) {
        if (!isSameQueryThanLastQuery(query)) {
            resetLastPageRequestedNumber();
            setLastQueryInfoRequested(query);
        }
        repoApiDataSource.getGithubRepos(query, getLastPageRequestedNumber(),
                                       new RepositoryListener<List<GithubRepoDomain>>() {
            @Override
            public void onSuccess(List<GithubRepoDomain> response) {
                increaseLastPageRequestedNumber();
                repoCacheDataSource.insert(response);
            }

            @Override
            public void onError(String errorMessage) {
                errorMessageLiveData.setValue(errorMessage);
            }
        });
        return repoCacheDataSource.getGithubRepos(query);
    }
    //endregion

    //region Private Methods
    private boolean isSameQueryThanLastQuery(String query) {
        return !TextUtils.isEmpty(getLastQueryInfoRequested()) &&
                getLastQueryInfoRequested().equalsIgnoreCase(query);
    }

    private void resetLastPageRequestedNumber() {
        this.lastPageRequestedNumber = 1;
    }

    private void increaseLastPageRequestedNumber() {
        this.lastPageRequestedNumber++;
    }
    //endregion
}
