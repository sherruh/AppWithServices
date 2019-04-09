package com.example.appwithservices;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;

public class SongsActivity extends AppCompatActivity {

    SongAdapter songAdapter;
    Intent intent;
    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        songAdapter = new SongAdapter(MainActivity.songList);
        recyclerView.setAdapter(songAdapter);
        songAdapter.setClickListener(new SongAdapter.ClickListener() {
            @Override
            public void onClick(int pos) {
                if (intent!=null)stopService(intent);
                String songName=MainActivity.songList.get(pos).get("file_name");
                String songPath=MainActivity.songList.get(pos).get("file_path");
                intent=new Intent(SongsActivity.this,MyIntentService.class);
                intent.putExtra("song",songName);
                startService(intent);
                controlPlayer(songPath);
            }
        });
    }

    private void controlPlayer(String songPath){

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
    }


}
