package com.franlopez.androidcertification.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.franlopez.androidcertification.api.call.GithubCalls;
import com.franlopez.androidcertification.data.domain.RepoDomain;
import com.franlopez.androidcertification.data.domain.RepoSearchDomain;
import com.franlopez.androidcertification.db.MyDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GithubRepository {
    private static GithubRepository sInstance;

    public static synchronized GithubRepository getInstance() {
        if (sInstance == null) {
            sInstance = new GithubRepository();
        }
        return sInstance;
    }

    private MutableLiveData<List<RepoDomain>> searchReposLiveData = new MutableLiveData<>();

    public LiveData<List<RepoDomain>> getSearchReposLiveData() {
        return searchReposLiveData;
    }

    public LiveData<List<RepoDomain>> searchRepos(String query, int page, int itemsPerPage) {
        if (searchReposLiveData != null &&
                searchReposLiveData.getValue() == null) {
            GithubCalls.searchRepositories(query, page, itemsPerPage, new RepositoryListener<RepoSearchDomain>() {
                @Override
                public void onSuccess(final RepoSearchDomain response) {
                    searchReposLiveData.setValue(response.getItems());
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            MyDatabase.getInstance().reposDao().insert(response.getItems());
                            MyDatabase.getInstance().reposDao().getRepos();
                        }
                    });
                }

                @Override
                public void onError(String errorMessage) {

                }
            });
        }
        return searchReposLiveData;
    }
}
