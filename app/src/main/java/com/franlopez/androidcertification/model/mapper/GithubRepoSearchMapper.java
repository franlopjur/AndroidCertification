package com.franlopez.androidcertification.model.mapper;

import com.franlopez.androidcertification.model.domain.GithubRepoSearchDomain;
import com.franlopez.androidcertification.model.dto.GithubRepoSearchDto;

public class GithubRepoSearchMapper {
    public static GithubRepoSearchDomain dtoToDomain(GithubRepoSearchDto dto) {
        GithubRepoSearchDomain domain = new GithubRepoSearchDomain();
        if (dto != null) {
            domain.setItems(GithubRepoMapper.dtoListToDomainList(dto.getItems()));
            domain.setNextPage(dto.getNextPage());
            domain.setTotalCount(dto.getTotalCount());
        }
        return domain;
    }
}
