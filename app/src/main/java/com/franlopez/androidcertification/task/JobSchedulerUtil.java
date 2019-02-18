package com.franlopez.androidcertification.task;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

public class JobSchedulerUtil {

    public static final boolean NO_FINALIZE_JOB_AFTER_EXECUTE = true;
    public static final boolean FINALIZE_JOB_AFTER_EXECUTE = false;
    public static final boolean RETRY_JOB_EXECUTION = true;
    public static final boolean NOT_RETRY_JOB_EXECUTION = false;

    //region Public Static Methods
    public static void scheduleJob(Context context, Class clazz, boolean charging) {
        ComponentName githubComponent = new ComponentName(context, clazz);
        getJobScheduler(context).schedule(generateJobInfo(githubComponent, charging).build());
    }
    //endregion

    //region Static Private Methods
    private static JobInfo.Builder generateJobInfo(ComponentName componentName, boolean charging) {
        JobInfo.Builder builder = new JobInfo.Builder(componentName.hashCode(), componentName);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//        builder.setOverrideDeadline(5000);
        builder.setMinimumLatency(10000);
        return builder.setRequiresCharging(charging);
    }

    private static JobScheduler getJobScheduler(Context context) {
        return (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }
    //endregion
}
