package com.example.remindme;


import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * This class is used to create an alarm for the reminder and notify the user when the alarm goes off using a notification
 */
public class Alarm extends BroadcastReceiver {

    final String TAG = "Alarm Class";


    /**
     * This method is used to create an alarm for the reminder and notify the user when the alarm goes off using a notification
     *
     * @param context
     * @param intent
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {

        //get the title of the reminder
        String reminderTitle = null;
        Log.d(TAG, "Alarm classs has title as : " + intent.getExtras().getString("reminderTitle"));
        reminderTitle = intent.getExtras().getString("reminderTitle");


        //create a notification channel for the reminder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(reminderTitle, "Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //create the notification
        String Channel_ID = reminderTitle;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Channel_ID);
        builder.setSmallIcon(R.drawable.test);
        builder.setContentTitle(reminderTitle);
        builder.setContentText(reminderTitle);

        //create the notification manager
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        //notify the user
        manager.notify(1, builder.build());
    }

}
