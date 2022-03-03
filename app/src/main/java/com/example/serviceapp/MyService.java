package com.example.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private MediaPlayer mediaPlayer;

    public MyService() {
        Log.i(TAG, "MyService: MY_SERVICE_CONSTRUCTOR_CALLED");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: SERVICE_CREATED");
        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.soch_liya_song);
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: SERVICE_STARTED");
        if(mediaPlayer!=null && !mediaPlayer.isPlaying() &&  intent.getAction().equals("PLAY_SONG")){
            mediaPlayer.start();
        }

        if(mediaPlayer!=null && mediaPlayer.isPlaying() && intent.getAction().equals("PAUSE_SONG")){
            mediaPlayer.pause();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: SERVICE_DESTROYED");
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}