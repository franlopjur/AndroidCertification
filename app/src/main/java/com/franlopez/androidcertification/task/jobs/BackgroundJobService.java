package com.franlopez.androidcertification.task.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.franlopez.androidcertification.data.domain.RepoDomain;
import com.franlopez.androidcertification.db.MyDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.franlopez.androidcertification.task.JobSchedulerUtil.FINALIZE_JOB_AFTER_EXECUTE;
import static com.franlopez.androidcertification.task.JobSchedulerUtil.RETRY_JOB_EXECUTION;

public abstract class BackgroundJobService extends JobService {

    abstract void executeInBackground();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeInBackground();
            }
        }).start();
        return FINALIZE_JOB_AFTER_EXECUTE;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return RETRY_JOB_EXECUTION;
    }
}
