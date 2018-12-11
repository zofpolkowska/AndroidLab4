package com.example.bzdeco.notificationservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;


public class NotificationService extends IntentService {

    public static final String TITLE = "servicedemo.notification.title";
    public static final String TEXT = "servicedemo.notification.text";
    public static final String FREQUENCY = "servicedemo.notification.frequency";
    public static final String TIMES = "servicedemo.notification.times";

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int notificationId = 0;
        String title = intent.getStringExtra(TITLE);
        String text = intent.getStringExtra(TEXT);
        int frequency = intent.getIntExtra(FREQUENCY, 5);
        int times = intent.getIntExtra(TIMES, 1);

        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(frequency * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Source: https://developer.android.com/training/notify-user/build-notification
            Notification notification = new NotificationCompat.Builder(this, MainActivity.NOTIFICATION_CHANNEL)
                    .setSmallIcon(R.drawable.agh)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .build();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(notificationId, notification);
            notificationId++;
        }
    }
}
