package com.franlopez.androidcertification.api.call;

import com.franlopez.androidcertification.api.ServiceFactory;
import com.franlopez.androidcertification.data.RepositoryListener;
import com.franlopez.androidcertification.data.domain.RepoSearchDomain;
import com.franlopez.androidcertification.data.dto.RepoSearchDto;
import com.franlopez.androidcertification.data.mapper.RepoSearchMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubCalls {
    public static void searchRepositories(String query, int page, int itemPerPage,
                                          final RepositoryListener<RepoSearchDomain> listener) {
        ServiceFactory
                .createGithubService()
                .searchRepositories(query, page, itemPerPage)
                .enqueue(new Callback<RepoSearchDto>() {
                    @Override
                    public void onResponse(Call<RepoSearchDto> call, Response<RepoSearchDto> response) {
                        listener.onSuccess(RepoSearchMapper.dtoToDomain(response.body()));
                    }

                    @Override
                    public void onFailure(Call<RepoSearchDto> call, Throwable t) {
                        listener.onError("Error");
                    }
                });
    }
}
