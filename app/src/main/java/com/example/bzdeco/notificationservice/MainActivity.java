package com.example.bzdeco.notificationservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL = "channel0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_settings);
    }

    public void onStartButtonClicked(View view) {
        Intent service = new Intent(this, NotificationService.class);
        EditText title = findViewById(R.id.notificationTitle);
        EditText text = findViewById(R.id.notificationContent);
        EditText frequency = findViewById(R.id.frequency);
        EditText times = findViewById(R.id.times);

        service.putExtra(NotificationService.TITLE, title.getText().toString());
        service.putExtra(NotificationService.TEXT, text.getText().toString());
        service.putExtra(NotificationService.FREQUENCY, Integer.parseInt(frequency.getText().toString()));
        service.putExtra(NotificationService.TIMES, Integer.parseInt(times.getText().toString()));

        startService(service);
    }

    // Source: https://developer.android.com/training/notify-user/build-notification
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel";
            String description = "Notification channel for service demo";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
