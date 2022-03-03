package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

    }

    private void pauseSong(){

    }
    private void stopSong(){

    }
}