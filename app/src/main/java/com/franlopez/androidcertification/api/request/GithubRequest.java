package com.franlopez.androidcertification.api.request;

import com.franlopez.androidcertification.api.ServiceFactory;
import com.franlopez.androidcertification.model.dto.GithubRepoSearchDto;

import retrofit2.Call;

public class GithubRequest {
    public static Call<GithubRepoSearchDto> searchRepositories(String query, int page, int itemPerPage) {
        return ServiceFactory
                .createGithubService()
                .searchRepositories(query, page, itemPerPage);
    }
}
