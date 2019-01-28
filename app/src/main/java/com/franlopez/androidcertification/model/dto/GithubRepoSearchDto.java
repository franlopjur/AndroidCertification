package com.franlopez.androidcertification.model.dto;

import com.franlopez.androidcertification.commons.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubRepoSearchDto {
    @SerializedName(Constants.DTO.GithubRepoSearch.TOTAL_COUNT)
    private int totalCount = 0;

    @SerializedName(Constants.DTO.GithubRepoSearch.ITEMS)
    private List<GithubRepoDto> items = new ArrayList();

    private Integer nextPage = null;

    public int getTotalCount() {
        return totalCount;
    }

    public List<GithubRepoDto> getItems() {
        return items;
    }

    public Integer getNextPage() {
        return nextPage;
    }
}
