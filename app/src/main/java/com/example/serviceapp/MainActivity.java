package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnPlay,btnPause,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(view -> playSong());
        btnPause.setOnClickListener(view -> pauseSong());
        btnStop.setOnClickListener(view -> stopSong());
    }

    private void playSong(){
        Intent intent = new Intent(this,MyService.class);
        intent.setAction("PLAY_SONG");
        try {
            startService(intent);
        }catch (Exception e){
            Log.e(TAG, "playSong: Error in starting service " + e.getLocalizedMessage() );
        }
    }

    private void pauseSong(){
        Intent intent = new Intent(this,MyService.class);
        intent.setAction("PAUSE_SONG");
        try {
            startService(intent);
        }catch (Exception e){
            Log.e(TAG, "playSong: Error in starting service in pause" + e.getLocalizedMessage() );
        }
    }
    private void stopSong(){
        Intent intent = new Intent(this,MyService.class);
        try {
            stopService(intent);
        }catch (Exception e){
            Log.e(TAG, "playSong: Error in stopping service " + e.getLocalizedMessage() );
        }
    }
}