package com.franlopez.androidcertification.data;

public abstract class GithubRepoDataSource<T> {
    protected abstract T getGithubRepos(String query, int page, RepositoryListener<T> callback);
    protected abstract T getGithubRepos(String query);
}
