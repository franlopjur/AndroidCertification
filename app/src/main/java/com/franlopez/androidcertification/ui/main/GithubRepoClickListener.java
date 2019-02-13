package com.franlopez.androidcertification.ui.main;

import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

public interface GithubRepoClickListener {
    void onItemClicked(int position, GithubRepoDomain repoDomain);
}
