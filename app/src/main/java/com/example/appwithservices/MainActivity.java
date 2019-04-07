package com.example.appwithservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Intent intentService;
    EditText et;
    ProgressBar progressBar;
    public static String a;
    public static String b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        a="im here!";
        b="im here too!";

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
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onClickStopIntent(View view) {
        if(intentService!=null) {
            stopService(intentService);
            progressBar.setVisibility(View.GONE);
        }
    }
}
