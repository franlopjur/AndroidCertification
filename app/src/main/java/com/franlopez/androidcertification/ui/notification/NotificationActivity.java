package com.franlopez.androidcertification.ui.notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.commons.NotificationUtils;
import com.franlopez.androidcertification.task.JobSchedulerUtil;
import com.franlopez.androidcertification.task.jobs.ChargingIndicatorJobService;

public class NotificationActivity extends AppCompatActivity {

    private Button notifyMeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initializeViews();
    }

    private void initializeViews() {
        notifyMeBtn = findViewById(R.id.notification__btn__notify_me);
        notifyMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        //JobSchedulerUtil.scheduleJob(this, ChargingIndicatorJobService.class, true);
    }

    private void sendNotification() {
        NotificationUtils.sendNotification(this,
                                           "Prueba de notificación - Título",
                                           "Prueba de notificación - Descripción");
    }
}
