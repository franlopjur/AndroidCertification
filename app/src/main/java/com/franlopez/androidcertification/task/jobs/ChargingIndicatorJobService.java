package com.franlopez.androidcertification.task.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

import com.franlopez.androidcertification.commons.NotificationUtils;

import static com.franlopez.androidcertification.task.JobSchedulerUtil.FINALIZE_JOB_AFTER_EXECUTE;
import static com.franlopez.androidcertification.task.JobSchedulerUtil.RETRY_JOB_EXECUTION;

public class ChargingIndicatorJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_SHORT).show();
        NotificationUtils.sendNotification(getApplicationContext(), "Cargando", "Actualmente: cargando");
        jobFinished(jobParameters, true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Toast.makeText(getApplicationContext(), "stop", Toast.LENGTH_SHORT).show();
        NotificationUtils.cancelNotification(getApplicationContext());
        return true;
    }
}
