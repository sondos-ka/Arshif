package com.example.arshif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadCast extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"notifyLemubit")
                .setSmallIcon(R.drawable.ic_add_black_24dp)
                .setContentTitle(context.getResources().getString(R.string.Remind) ).setContentText(context.getResources().getString(R.string.RemindMessage))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());


    }
}
