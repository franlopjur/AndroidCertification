package com.franlopez.androidcertification.data.mapper;

import com.franlopez.androidcertification.data.domain.RepoDomain;
import com.franlopez.androidcertification.data.dto.RepoDto;

import java.util.ArrayList;
import java.util.List;

public class RepoMapper {
    public static List<RepoDomain> dtoListToDomainList(List<RepoDto> dtoList) {
        List<RepoDomain> domainList = new ArrayList<>();
        if (dtoList != null &&
                !dtoList.isEmpty()) {
            for (RepoDto dto : dtoList) {
                domainList.add(dtoToDomain(dto));
            }
        }
        return domainList;
    }

    public static RepoDomain dtoToDomain(RepoDto dto) {
        RepoDomain domain = new RepoDomain();
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
}
