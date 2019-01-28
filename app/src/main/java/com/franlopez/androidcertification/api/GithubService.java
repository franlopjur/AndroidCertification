package com.franlopez.androidcertification.api;

import com.franlopez.androidcertification.model.dto.GithubRepoSearchDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    @GET("search/repositories?sort=stars")
    public Call<GithubRepoSearchDto> searchRepositories(@Query("q") String query,
                                                        @Query("page") int page,
                                                        @Query("per_page") int itemPerPage);

}
