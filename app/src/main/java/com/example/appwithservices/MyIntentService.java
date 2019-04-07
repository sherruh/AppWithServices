package com.example.appwithservices;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyIntentService extends IntentService {

    boolean isStopped;
    NotificationManager manager;

    public MyIntentService() {
        super("");
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        startTask();
    }




    private void startTask(){
        showNotification();
        for(int i=0;i<10;i++){
            if (isStopped) return;
            Log.d("MyApp","Service task on "+i+" step");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
            NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle(getString(R.string.app_name)) // title for notification
                    .setContentText("works") // message for notification
                    .setAutoCancel(true);
            manager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, mBuilder.build());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyApp","MyIntentService stopped");
        isStopped=true;
        manager.cancel(0);
    }
}
