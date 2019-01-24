package com.franlopez.androidcertification.data.mapper;

import com.franlopez.androidcertification.data.domain.RepoDomain;
import com.franlopez.androidcertification.data.domain.RepoSearchDomain;
import com.franlopez.androidcertification.data.dto.RepoDto;
import com.franlopez.androidcertification.data.dto.RepoSearchDto;

import java.util.ArrayList;
import java.util.List;

public class RepoSearchMapper {
    public static RepoSearchDomain dtoToDomain(RepoSearchDto dto) {
        RepoSearchDomain domain = new RepoSearchDomain();
        if (dto != null) {
            domain.setItems(RepoMapper.dtoListToDomainList(dto.getItems()));
            domain.setNextPage(dto.getNextPage());
            domain.setTotalCount(dto.getTotalCount());
        }
        return domain;
    }
}
