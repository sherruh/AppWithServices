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

    public enum ControlPlayer{
        PLAY,
        PAUSE,
        RESUME,
        NEXT,
        PREV
    }

    SongAdapter songAdapter;
    Intent intent;
    public static MediaPlayer mediaPlayer;
    public static ControlPlayer statusPlayer;
    private static SongsActivity ins;
    private int songNumber;

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
                songNumber=pos;
                startPlay();
            }
        });
        ins=this;
    }

    private void startPlay(){
        statusPlayer=ControlPlayer.PLAY;
        startIntentService();
        controlPlayer(statusPlayer);
    }

    public void startIntentService(){
        if (intent!=null)stopService(intent);
        String songName=MainActivity.songList.get(songNumber).get("file_name");
        intent=new Intent(SongsActivity.this,MyIntentService.class);
        intent.putExtra("song",songName);
        startService(intent);
    }

    public static SongsActivity getInstance(){
        return ins;
    }

    public void controlPlayer(ControlPlayer mstatusPlayer){

        switch (mstatusPlayer){
            case PREV:
                songNumber--;
                startPlay();
            case NEXT:
                songNumber++;
                startPlay();
                break;
            case PLAY:
                if (mediaPlayer==null){mediaPlayer=new MediaPlayer();}
                else{mediaPlayer.reset();
                    Log.d("MyApp","reset succes");
                }
                mediaPlayer.stop();
                try {
                    mediaPlayer.setDataSource(MainActivity.songList.get(songNumber).get("file_path"));
                    mediaPlayer.prepare();
                    Log.d("MyApp","set Source succes");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                break;
            case PAUSE:
                startIntentService();
                break;
            case RESUME:
                startIntentService();
        }
    }


}
