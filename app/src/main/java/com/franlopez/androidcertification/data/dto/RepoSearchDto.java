package com.franlopez.androidcertification.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepoSearchDto {
    @SerializedName("total_count")
    int totalCount = 0;

    @SerializedName("items")
    List<RepoDto> items = new ArrayList();

    Integer nextPage = null;

    public int getTotalCount() {
        return totalCount;
    }

    public List<RepoDto> getItems() {
        return items;
    }

    public Integer getNextPage() {
        return nextPage;
    }
}
