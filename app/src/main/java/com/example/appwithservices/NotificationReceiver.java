package com.example.appwithservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("NEXT_ACTION".equals(action)) {
            Toast.makeText(context, "NEXT CALLED", Toast.LENGTH_SHORT).show();
            Log.d("MyApp",MainActivity.a);
        }if ("PREV_ACTION".equals(action)) {
            Toast.makeText(context, "PREV CALLED", Toast.LENGTH_SHORT).show();
            Log.d("MyApp",MainActivity.b);
        }
    }
}
