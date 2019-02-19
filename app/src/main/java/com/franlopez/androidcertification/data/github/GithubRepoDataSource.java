package com.franlopez.androidcertification.data.github;

import com.franlopez.androidcertification.data.RepositoryListener;

public abstract class GithubRepoDataSource<T> {
    protected abstract T getGithubRepos(String query, int page, RepositoryListener<T> callback);
    protected abstract T getGithubRepos(String query);
}
