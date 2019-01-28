package com.franlopez.androidcertification.data;

import android.arch.lifecycle.LiveData;

import com.franlopez.androidcertification.db.MyDatabase;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GithubRepoCacheDataSource extends GithubRepoDataSource<LiveData<List<GithubRepoDomain>>> {

    //region Insert or Delete Methods
    void insert(final List<GithubRepoDomain> list) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MyDatabase.getInstance().reposDao().insert(list);
            }
        });
    }
    //endregion

    //region Interface DataSource Methods
    @Override
    protected LiveData<List<GithubRepoDomain>> getGithubRepos(String query, int page, RepositoryListener<LiveData<List<GithubRepoDomain>>> callback) {
        return getGithubRepos(query);
    }

    @Override
    protected LiveData<List<GithubRepoDomain>> getGithubRepos(String query) {
        String queryLike = "";
        if (query != null) {
            queryLike = query.replace(" ", "%");
            queryLike = "%" + queryLike + "%";
        }
        return MyDatabase.getInstance().reposDao().getReposAsync(queryLike);
    }
    //endregion
}
