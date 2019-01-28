package com.franlopez.androidcertification.task.jobs;

import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.db.MyDatabase;

import java.util.List;

public class CustomJobService extends BackgroundJobService {

    List<GithubRepoDomain> repoList;

    public CustomJobService(List<GithubRepoDomain> repoList) {
        this.repoList = repoList;
    }

    @Override
    void executeInBackground() {
        MyDatabase.getInstance().reposDao().insert(repoList);
    }
}
