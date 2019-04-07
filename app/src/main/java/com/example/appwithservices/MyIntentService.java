package com.example.appwithservices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {

    boolean isStopped;

    public MyIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        startTask();
    }

    private void startTask(){
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyApp","MyIntentService stopped");
        isStopped=true;
    }
}
