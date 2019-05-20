package com.example.simplealarmclock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import org.jetbrains.annotations.Nullable;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String get_key= intent.getExtras().getString("extra");
        if(get_key.equals("on"))
        {
            id=1;
        }
        else if(get_key.equals("off")){
            id=0;
        }
        if(id==1)
        {
            mediaPlayer=MediaPlayer.create(this,R.raw.baothuc);
            mediaPlayer.start();
            id=0;
        }
        else if(id==0) {
            mediaPlayer.stop();
            mediaPlayer.reset();

        }

        return START_NOT_STICKY;
    }
}
