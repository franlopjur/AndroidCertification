package com.franlopez.androidcertification.task.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.franlopez.androidcertification.data.domain.RepoDomain;
import com.franlopez.androidcertification.db.MyDatabase;

import java.util.List;

public class CustomJobService extends BackgroundJobService {

    List<RepoDomain> repoList;

    public CustomJobService(List<RepoDomain> repoList) {
        this.repoList = repoList;
    }

    @Override
    void executeInBackground() {
        MyDatabase.getInstance().reposDao().insert(repoList);
    }
}
