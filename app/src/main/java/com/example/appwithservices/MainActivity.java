package com.example.appwithservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View view) {
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
    }

    public void onClickStop(View view) {
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);
    }

    public void onClickStartIntent(View view) {
        intentService =new Intent(this,MyIntentService.class);
        startService(intentService);
    }

    public void onClickStopIntent(View view) {
        if(intentService!=null) stopService(intentService);
    }
}
