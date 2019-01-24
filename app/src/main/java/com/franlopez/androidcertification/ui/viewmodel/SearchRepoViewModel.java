package com.franlopez.androidcertification.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.franlopez.androidcertification.data.GithubRepository;
import com.franlopez.androidcertification.data.domain.RepoDomain;

import java.util.List;

public class SearchRepoViewModel extends ViewModel {

    private LiveData<List<RepoDomain>> searchReposLiveData = GithubRepository.getInstance().getSearchReposLiveData();

    public LiveData<List<RepoDomain>> getSearchReposLiveData() {
        return searchReposLiveData;
    }

    public void searchRepos(String query, int page, int itemsPerPage) {
        searchReposLiveData = GithubRepository.getInstance().searchRepos(query, page, itemsPerPage);
    }
}
