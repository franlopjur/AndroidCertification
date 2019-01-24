package com.franlopez.androidcertification.task;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

public class UseCaseUtil {

    public static final boolean EXECUTE_JOB_SYNC = true;
    public static final boolean EXECUTE_JOB_ASYNC = false;
    public static final boolean RETRY_JOB_EXECUTION = true;
    public static final boolean NOT_RETRY_JOB_EXECUTION = false;

    //region Public Static Methods
    public static void scheduleJob(Context context, Class clazz) {
        ComponentName githubComponent = new ComponentName(context, clazz);
        getJobScheduler(context).schedule(generateJobInfo(context, githubComponent).build());
    }
    //endregion

    //region Static Private Methods
    private static JobInfo.Builder generateJobInfo(Context context, ComponentName componentName) {
        List<JobInfo> jobList = getJobScheduler(context).getAllPendingJobs();
        int jobId = !jobList.isEmpty() ? jobList.size() + 1 : 0;
        JobInfo.Builder builder = new JobInfo.Builder(jobId, componentName);
        builder.setRequiresCharging(false);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setOverrideDeadline(0);
        return builder.setRequiresCharging(false);
    }

    private static JobScheduler getJobScheduler(Context context) {
        return (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }
    //endregion
}
