package com.example.softwareengrproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: substitute assignment names in notification
        PendingIntent pi = PendingIntent.getActivity(context,0, new Intent(context, AccountScreen.class),0);
        Log.i("BR", "Broadcast Received!");


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.registerscreenpic)
                .setContentTitle("AssignmentBuddy Reminder")
                .setContentText("Assignment Due")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pi);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, builder.build());

    }
}
