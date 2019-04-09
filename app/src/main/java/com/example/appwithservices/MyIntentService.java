package com.example.appwithservices;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.io.IOException;

public class MyIntentService extends IntentService {


    NotificationManager manager;
    MediaPlayer mediaPlayer;

    public MyIntentService() {
        super("");
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String songPath=intent.getStringExtra("song");
        songSelect(songPath);
    }




    private void songSelect(String songPath){
        if (mediaPlayer==null){mediaPlayer=new MediaPlayer();}
            else{mediaPlayer.reset();
            Log.d("MyApp","reset succes");
        }
        mediaPlayer.stop();
        try {
            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            Log.d("MyApp","set Source succes");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        showNotification();
    }

    private void showNotification() {
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.O){
        NotificationChannel channel=new NotificationChannel("my_channel",
                "new_message", NotificationManager.IMPORTANCE_HIGH);
        manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        NotificationCompat.Builder notification=new NotificationCompat.Builder(getBaseContext(),"my_channel");
        notification.setContentTitle(getString(R.string.app_name));
        notification.setContentText("works");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        startForeground(1,notification.build());
        }else{
            Intent pressIntent = new Intent(this, MyService.class);
            pressIntent.setClassName("com.example.appwithservices","MainActivity");
            pressIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pressIntent.putExtra("wzrk_id","wzrk_id");
            PendingIntent pressPendingIntent = PendingIntent.getService(getApplicationContext(), 0, pressIntent, 0);

            Intent nextReceive = new Intent();//attach all keys starting with wzrk_ to your notification extras
            nextReceive.setAction("NEXT_ACTION");//replace with your custom value
            PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 12345, nextReceive, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent prevReceive = new Intent();//attach all keys starting with wzrk_ to your notification extras
            prevReceive.setAction("PREV_ACTION");//replace with your custom value
            PendingIntent prevPendingIntent = PendingIntent.getBroadcast(this, 12345, prevReceive, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder notificationBuilder =   new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle(getString(R.string.app_name)) // title for notification
                    .setContentText("works") // message for notification
                    .setAutoCancel(true)
                    .setContentIntent(pressPendingIntent)
                    .addAction(android.R.drawable.ic_input_add, "Next", nextPendingIntent)
                    .addAction(android.R.drawable.ic_input_delete, "Prev", prevPendingIntent);
            manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, notificationBuilder.build());
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyApp","MyIntentService stopped");
        manager.cancel(0);
    }
}
