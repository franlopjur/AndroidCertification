package com.franlopez.androidcertification.model.mapper;

import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.model.dto.GithubRepoDto;

import java.util.ArrayList;
import java.util.List;

public class GithubRepoMapper {
    //region Public Methods
    public static List<GithubRepoDomain> dtoListToDomainList(List<GithubRepoDto> dtoList) {
        List<GithubRepoDomain> domainList = new ArrayList<>();
        if (dtoList != null &&
                !dtoList.isEmpty()) {
            for (GithubRepoDto dto : dtoList) {
                domainList.add(dtoToDomain(dto));
            }
        }
        return domainList;
    }
    //endregion

    //region Private Methods
    private static GithubRepoDomain dtoToDomain(GithubRepoDto dto) {
        GithubRepoDomain domain = new GithubRepoDomain();
        if (dto != null) {
            domain.setDescription(dto.getDescription());
            domain.setForks(dto.getForks());
            domain.setFullName(dto.getFullName());
            domain.setId(dto.getId());
            domain.setLanguage(dto.getLanguage());
            domain.setName(dto.getName());
            domain.setStars(dto.getStars());
            domain.setUrl(dto.getUrl());
        }
        return domain;
    }
    //endregion
}
