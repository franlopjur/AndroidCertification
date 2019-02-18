package com.franlopez.androidcertification.commons;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.ui.main.activity.MainActivity;

import static android.support.v4.app.NotificationCompat.DEFAULT_ALL;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;

public class NotificationUtils {
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;

    public static void sendNotification(Context context, String title, String description) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(PRIMARY_CHANNEL_ID,
                                            "Prueba",
                                            NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Prueba de notificación por canal");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(NOTIFICATION_ID, getNotificationBuilder(context, title, description).build());
    }

    public static void cancelNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private static NotificationCompat.Builder getNotificationBuilder(Context context, String title, String description) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentText(description)
                .setContentTitle(title)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(PRIORITY_HIGH)
                .setDefaults(DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher);

    }
}
