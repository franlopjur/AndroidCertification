package com.franlopez.androidcertification.data.domain;

import com.franlopez.androidcertification.data.dto.RepoDto;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepoSearchDomain {
    private int totalCount = 0;
    private List<RepoDomain> items = new ArrayList();
    private Integer nextPage = null;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<RepoDomain> getItems() {
        return items;
    }

    public void setItems(List<RepoDomain> items) {
        this.items = items;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
