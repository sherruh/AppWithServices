package com.example.appwithservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SongsActivity extends AppCompatActivity {

    SongAdapter songAdapter;

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
                String songPath=MainActivity.songList.get(pos).get("file_path");
                Intent intent=new Intent(SongsActivity.this,MyIntentService.class);
                intent.putExtra("song",songPath);
                startService(intent);
            }
        });
    }
}
