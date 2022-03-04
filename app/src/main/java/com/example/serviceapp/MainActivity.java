package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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
        intent.setAction(getString(R.string.play_song));
        try {
            Toast.makeText(this, "Playing song", Toast.LENGTH_SHORT).show();
            startService(intent);
        }catch (Exception e){
            Log.e(TAG, "playSong: Error in starting service " + e.getLocalizedMessage() );
        }
    }

    private void pauseSong(){
        Intent intent = new Intent(this,MyService.class);
        intent.setAction(getString(R.string.pause_song));
        try {
            Toast.makeText(this, "Song paused", Toast.LENGTH_SHORT).show();
            startService(intent);
        }catch (Exception e){
            Log.e(TAG, "playSong: Error in starting service in pause" + e.getLocalizedMessage() );
        }
    }
    private void stopSong(){
        Intent intent = new Intent(this,MyService.class);
        try {
            Toast.makeText(this, "Song stopped", Toast.LENGTH_SHORT).show();
            stopService(intent);
        }catch (Exception e){
            Log.e(TAG, "playSong: Error in stopping service " + e.getLocalizedMessage() );
        }
    }
}