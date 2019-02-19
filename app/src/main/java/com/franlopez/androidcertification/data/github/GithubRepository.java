package com.franlopez.androidcertification.data.github;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.text.TextUtils;

import com.franlopez.androidcertification.SingleLiveEvent;
import com.franlopez.androidcertification.commons.Constants;
import com.franlopez.androidcertification.data.RepositoryListener;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.ui.main.GithubRepoBoundaryCallback;

import java.util.List;

public class GithubRepository {
    //region Members
    private GithubRepoApiDataSource repoApiDataSource;
    private GithubRepoCacheDataSource repoCacheDataSource;

    private GithubRepoBoundaryCallback callback;
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
    //endregion

    //region Public Methods
    public LiveData<PagedList<GithubRepoDomain>> searchRepos(final String query) {
        if (!isSameQueryThanLastQuery(query) ||
                callback == null) {
            callback = new GithubRepoBoundaryCallback(query, repoApiDataSource, repoCacheDataSource);
            setLastQueryInfoRequested(query);
            setUpLastPageNumberToQuery(query);
        }
        DataSource.Factory<Integer, GithubRepoDomain> factory = repoCacheDataSource.getGithubRepos(query);
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true).
                setInitialLoadSizeHint(Constants.ITEMS_PER_PAGE_DB).
                setPageSize(Constants.ITEMS_PER_PAGE_DB).
                setPrefetchDistance(Constants.PREFETCH_DISTANCE).build();
        LivePagedListBuilder livePagedBuilder = new LivePagedListBuilder(factory, config);
        livePagedBuilder.setBoundaryCallback(callback);
        return livePagedBuilder.build();
    }
    //endregion

    //region Private Methods
    private boolean isSameQueryThanLastQuery(String query) {
        return !TextUtils.isEmpty(getLastQueryInfoRequested()) &&
                getLastQueryInfoRequested().equalsIgnoreCase(query);
    }

    private String getLastQueryInfoRequested() {
        return lastQueryInfoRequested;
    }

    private void setLastQueryInfoRequested(String query) {
        this.lastQueryInfoRequested = query;
    }

    private void setUpLastPageNumberToQuery(String query) {
        repoCacheDataSource.getGithubReposSync(query, new RepositoryListener<List<GithubRepoDomain>>() {
            @Override
            public void onSuccess(List<GithubRepoDomain> response) {
                if (response != null &&
                        !response.isEmpty()) {
                    callback.setLastPageRequestedNumber(((response.size() / Constants.ITEMS_PER_PAGE_NETWORK) + 1));
                }
            }

            @Override
            public void onError(String errorMessage) {
                //- DO ANYTHING
            }
        });
    }
    //endregion
}
