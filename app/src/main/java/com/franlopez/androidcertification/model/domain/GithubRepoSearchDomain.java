package com.franlopez.androidcertification.model.domain;

import java.util.ArrayList;
import java.util.List;

public class GithubRepoSearchDomain {
    private int totalCount = 0;
    private List<GithubRepoDomain> items = new ArrayList();
    private Integer nextPage = null;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GithubRepoDomain> getItems() {
        return items;
    }

    public void setItems(List<GithubRepoDomain> items) {
        this.items = items;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
