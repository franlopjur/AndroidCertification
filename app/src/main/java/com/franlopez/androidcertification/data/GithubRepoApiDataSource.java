package com.franlopez.androidcertification.data;

import com.franlopez.androidcertification.api.request.GithubRequest;
import com.franlopez.androidcertification.commons.Constants;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.model.dto.GithubRepoSearchDto;
import com.franlopez.androidcertification.model.mapper.GithubRepoMapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.franlopez.androidcertification.commons.Constants.ITEMS_PER_PAGE_NETWORK;

public class GithubRepoApiDataSource extends GithubRepoDataSource<List<GithubRepoDomain>> {
    @Override
    public List<GithubRepoDomain> getGithubRepos(String query, int page, final RepositoryListener<List<GithubRepoDomain>> callback) {
        GithubRequest.searchRepositories(query, page, ITEMS_PER_PAGE_NETWORK).enqueue(new Callback<GithubRepoSearchDto>() {
            @Override
            public void onResponse(Call<GithubRepoSearchDto> call, Response<GithubRepoSearchDto> response) {
                if (response.isSuccessful() &&
                        response.body() != null) {
                    callback.onSuccess(GithubRepoMapper.dtoListToDomainList(response.body().getItems()));

                } else {
                    callback.onError("Error");
                }
            }

            @Override
            public void onFailure(Call<GithubRepoSearchDto> call, Throwable t) {
                callback.onError("Error al realizar la llamada");
            }
        });
        return null;
    }

    @Override
    protected List<GithubRepoDomain> getGithubRepos(String query) {
        return null;
    }
}
