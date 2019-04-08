package com.example.appwithservices;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Intent intentService;
    EditText et;
    ProgressBar progressBar;
    public static String a;
    public static String b;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        a="im here!";
        b="im here too!";
        ArrayList<HashMap<String,String>> songList=new SongsManager().getPlayList("/sdcard/");

        if(songList!=null){
            for(int i=0;i<songList.size();i++){
                String fileName=songList.get(i).get("file_name");
                String filePath=songList.get(i).get("file_path");
                Log.d("MyApp"," name ="+fileName +" path = "+filePath);
            }
        }
        mediaPlayer=new MediaPlayer();
        String filePath = songList.get(0).get("file_path");
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            Log.d("MyApp","set Dource succes");
        } catch (IOException e) {
            e.printStackTrace();

        }
        mediaPlayer.start();

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
