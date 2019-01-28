package com.franlopez.androidcertification.ui.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.franlopez.androidcertification.data.GithubRepository;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

import java.util.List;

public class SearchRepoViewModel extends ViewModel {

    //region Members
    private GithubRepository githubRepository;
    private MutableLiveData<String> queryLiveData = new MutableLiveData<>();


    private LiveData<String> errorMessageLiveData;
    private LiveData<List<GithubRepoDomain>> githubReposListFromRepositoryLiveData = Transformations.switchMap(queryLiveData, new Function<String, LiveData<List<GithubRepoDomain>>>() {
        @Override
        public LiveData<List<GithubRepoDomain>> apply(String query) {
            return searchRepos(query);
        }
    });
    //endregion

    //region Constructors
    public SearchRepoViewModel() {
        githubRepository = new GithubRepository();
        this.errorMessageLiveData = githubRepository.getErrorMessageLiveData();
    }
    //endregion

    //region Getters
    public LiveData<List<GithubRepoDomain>> getSearchReposLiveData() {
        return githubReposListFromRepositoryLiveData;
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }
    //endregion

    //region Public Methods
    public void updateQuery(String newQuery) {
        queryLiveData.setValue(newQuery);
    }
    //endregion

    //region Private Methods
    private LiveData<List<GithubRepoDomain>> searchRepos(String query) {
        return githubRepository.searchRepos(query);
    }
    //endregion
}
