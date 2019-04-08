package com.example.appwithservices;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    ArrayList<HashMap<String,String>> songList;

    private ClickListener clickListener;
    public interface ClickListener {
        void onClick(int pos);
    }


    public SongAdapter(ArrayList<HashMap<String,String>> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.song_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvSongName.setText(songList.get(i).get("file_name"));
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSongName;
        ImageView ivPlayPause;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSongName=itemView.findViewById(R.id.tv_song_name);
            ivPlayPause=itemView.findViewById(R.id.iv_play_pause);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
