package com.example.remindme;



import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class Alarm extends BroadcastReceiver {




    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
//        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
//        mediaPlayer.start();
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= 34) {
            powerManager.isAllowedInLowPowerStandby(PowerManager.LOW_POWER_STANDBY_ALLOWED_REASON_TEMP_POWER_SAVE_ALLOWLIST);
        }

        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        final String TAG = "MyAdaptor";
        //get the title of the reminder
        String reminderTitle = null;
        Log.d(TAG, "Alarm classs has title as : " + intent.getExtras().getString("reminderTitle"));
        reminderTitle = intent.getExtras().getString("reminderTitle");



       // NotificationMaker notificationMaker = new NotificationMaker(context);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(reminderTitle,"Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        String Channel_ID = reminderTitle;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Channel_ID);
        builder.setSmallIcon(R.drawable.test);
        builder.setContentTitle(reminderTitle);
        builder.setContentText(reminderTitle);



        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(1, builder.build());
    }

}
