package com.franlopez.androidcertification.task;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

public class JobSchedulerUtil {

    public static final boolean NO_FINALIZE_JOB_AFTER_EXECUTE = true;
    public static final boolean FINALIZE_JOB_AFTER_EXECUTE = false;
    public static final boolean RETRY_JOB_EXECUTION = true;
    public static final boolean NOT_RETRY_JOB_EXECUTION = false;

    //region Public Static Methods
    public static void scheduleJob(Context context, Class clazz) {
        ComponentName githubComponent = new ComponentName(context, clazz);
        getJobScheduler(context).schedule(generateJobInfo(githubComponent).build());
    }
    //endregion

    //region Static Private Methods
    private static JobInfo.Builder generateJobInfo(ComponentName componentName) {
        JobInfo.Builder builder = new JobInfo.Builder(componentName.hashCode(), componentName);
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
