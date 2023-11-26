package com.example.remindme;



import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
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
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();

        //get the title of the reminder
        String reminderTitle = intent.getStringExtra("reminderTitle");

       // NotificationMaker notificationMaker = new NotificationMaker(context);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(reminderTitle,"Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        final String Channel_ID = reminderTitle;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Channel_ID);
        builder.setSmallIcon(R.drawable.test);
        builder.setContentTitle(reminderTitle);
        builder.setContentText(reminderTitle);



        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(1, builder.build());
    }

}
