package com.example.appwithservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    private SongsActivity ins;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(ins==null){
            ins=SongsActivity.getInstance();
        }

        String action = intent.getAction();
        if ("NEXT_ACTION".equals(action)) {
            Toast.makeText(context, "NEXT CALLED", Toast.LENGTH_SHORT).show();
            ins.controlPlayer(SongsActivity.ControlPlayer.NEXT);
        }else if ("PREV_ACTION".equals(action)) {
            Toast.makeText(context, "PREV CALLED", Toast.LENGTH_SHORT).show();
            ins.controlPlayer(SongsActivity.ControlPlayer.PREV);
        }else if("PLAY_PAUSE_ACTION".equals(action)){
            Toast.makeText(context, "PAUSE_PLAY CALLED", Toast.LENGTH_SHORT).show();
            if(SongsActivity.statusPlayer== SongsActivity.ControlPlayer.PLAY
                ||SongsActivity.statusPlayer== SongsActivity.ControlPlayer.RESUME) {
                SongsActivity.mediaPlayer.pause();
                SongsActivity.statusPlayer= SongsActivity.ControlPlayer.PAUSE;
                ins.controlPlayer(SongsActivity.ControlPlayer.PAUSE);
            }else if(SongsActivity.statusPlayer== SongsActivity.ControlPlayer.PAUSE){
                SongsActivity.mediaPlayer.start();
                SongsActivity.statusPlayer= SongsActivity.ControlPlayer.RESUME;
                ins.controlPlayer(SongsActivity.ControlPlayer.RESUME);
            }
        }
    }
}
