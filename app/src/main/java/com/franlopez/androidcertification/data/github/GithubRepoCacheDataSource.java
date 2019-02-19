package com.franlopez.androidcertification.data.github;

import android.arch.paging.DataSource;

import com.franlopez.androidcertification.data.RepositoryListener;
import com.franlopez.androidcertification.db.MyDatabase;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GithubRepoCacheDataSource extends GithubRepoDataSource<DataSource.Factory<Integer, GithubRepoDomain>> {

    //region Insert or Delete Methods
    public void insert(final List<GithubRepoDomain> list) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MyDatabase.getInstance().reposDao().insert(list);
            }
        });
    }

    public void getGithubReposSync(final String query, final RepositoryListener callback) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(MyDatabase.getInstance().reposDao().getRepos(transformQueryToLike(query)));
            }
        });
    }
    //endregion

    //region Interface DataSource Methods
    @Override
    protected DataSource.Factory<Integer, GithubRepoDomain> getGithubRepos(String query, int page,
                                                                   RepositoryListener<DataSource.Factory<Integer, GithubRepoDomain>> callback) {
        return getGithubRepos(query);
    }

    @Override
    protected DataSource.Factory<Integer, GithubRepoDomain> getGithubRepos(String query) {
        return MyDatabase.getInstance().reposDao().getReposAsync(transformQueryToLike(query));
    }
    //endregion

    //region Private Methods
    private String transformQueryToLike(String query) {
        String queryLike = "";
        if (query != null) {
            queryLike = query.replace(" ", "%");
            queryLike = "%" + queryLike + "%";
        }
        return queryLike;
    }
    //endregion
}
