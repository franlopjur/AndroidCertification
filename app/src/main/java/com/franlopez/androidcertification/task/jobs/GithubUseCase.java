package com.franlopez.androidcertification.task.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.franlopez.androidcertification.api.ServiceFactory;
import com.franlopez.androidcertification.data.RepoSearchDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.franlopez.androidcertification.task.UseCaseUtil.EXECUTE_JOB_SYNC;
import static com.franlopez.androidcertification.task.UseCaseUtil.RETRY_JOB_EXECUTION;

public class GithubUseCase extends JobService {

    public static  final String TAG = GithubUseCase.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        ServiceFactory.createGithubService().searchRepositories("android", 1, 10).enqueue(new Callback<RepoSearchDto>() {
            @Override
            public void onResponse(Call<RepoSearchDto> call, Response<RepoSearchDto> response) {
                Log.d(TAG, "onResponse");
            }

            @Override
            public void onFailure(Call<RepoSearchDto> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
        return EXECUTE_JOB_SYNC;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return RETRY_JOB_EXECUTION;
    }
}
